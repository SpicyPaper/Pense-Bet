package ch.arc.pensebet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class UpdateBetController {
	
	@Autowired
	IUserService userService;

	@Autowired
	private IBetService betService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyy-MM-dd"), true, 10));
	}
	
	@GetMapping("/bet/admin/all/{page}")
    public ModelAndView adminAllBets(@PathVariable("page") int page, Authentication authentication, Model model) {
		ModelAndView modelAndView = new ModelAndView("bets/personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 3);
    	Page<Bet> betPage = betService.findAll(pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        modelAndView.addObject("user", userService.findUserByNickname(authentication.getName()));
        modelAndView.addObject("canModify", true);
        modelAndView.addObject("canDelete", true);
        modelAndView.addObject("pagePagination", "admin");
        return modelAndView;
    }
	
	@PostMapping("/bet/{id}/delete")
    public ModelAndView deleteBet(@PathVariable("id") Integer id, Authentication authentication, Model model) {
		System.out.println("BET ID : " + id);
		Bet bet = betService.findBetById(id).get();
		betService.deleteBet(bet);
		ModelAndView modelAndView = new ModelAndView("redirect:/bet/admin/all/1");
        return modelAndView;
    }
	
	@GetMapping("/bet/moderator/all/{page}")
    public ModelAndView moderatorAllBets(@PathVariable("page") int page, Authentication authentication, Model model) {
		ModelAndView modelAndView = new ModelAndView("bets/personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 3);
    	Page<Bet> betPage = betService.findAll(pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        modelAndView.addObject("user", userService.findUserByNickname(authentication.getName()));
        modelAndView.addObject("canModify", true);
        modelAndView.addObject("pagePagination", "modo");
        return modelAndView;
    }
	
	@GetMapping("/bet/{id}/update")
    public ModelAndView showUpdateBet(@PathVariable("id") Integer id, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();
		
		ModelAndView modelAndView = new ModelAndView("bets/update-bet");
		modelAndView.addObject("bet", bet);
        return modelAndView;
    }
	
	@PostMapping("/bet/{id}/update")
    public ModelAndView updateBet(@PathVariable("id") Integer id, @ModelAttribute Bet updatedBet, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();

		bet.setSubject(updatedBet.getSubject());
		bet.setAmount(updatedBet.getAmount());
		bet.setEndingDate(updatedBet.getEndingDate());
		
		betService.saveBet(bet);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/bet/moderator/all/1");
        return modelAndView;
    }
}
