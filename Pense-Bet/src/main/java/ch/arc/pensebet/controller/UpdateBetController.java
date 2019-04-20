package ch.arc.pensebet.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

public class UpdateBetController {
	
	@Autowired
	IUserService userService;

	@Autowired
	private IBetService betService;
	
	@GetMapping("/bet/admin/all/{page}")
    public ModelAndView adminAllBets(@PathVariable("page") int page, Authentication authentication, Model model) {
		ModelAndView modelAndView = new ModelAndView("personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 15);
    	Page<Bet> betPage = betService.findAll(pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        modelAndView.addObject("user", userService.findUserByNickname(authentication.getName()));
        return modelAndView;
    }
	
	@PostMapping("/bet/{id}/delete")
    public ModelAndView deleteBet(@PathVariable("id") Integer id, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();
		betService.deleteBet(bet);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/bet/admin/all/1");
        return modelAndView;
    }
	
	@GetMapping("/bet/moderator/all/{page}")
    public ModelAndView moderatorAllBets(@PathVariable("page") int page, Authentication authentication, Model model) {
		ModelAndView modelAndView = new ModelAndView("personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 15);
    	Page<Bet> betPage = betService.findAll(pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        modelAndView.addObject("user", userService.findUserByNickname(authentication.getName()));
        return modelAndView;
    }
	
	@PostMapping("/bet/{id}/update")
    public ModelAndView updateBet(@PathVariable("id") Integer id, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();
		// TODO ...
		betService.saveBet(bet);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/bet/moderator/all/1");
        return modelAndView;
    }
}
