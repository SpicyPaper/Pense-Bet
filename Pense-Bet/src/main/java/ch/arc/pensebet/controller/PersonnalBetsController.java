package ch.arc.pensebet.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class PersonnalBetsController {
	// Pagination adapted from: https://dzone.com/articles/simple-pagination-example-with-spring-data-and-thy
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBetService betService;

    @GetMapping("/bet/user/created/active/{page}")
    public ModelAndView personnalBetsActive(@PathVariable("page") int page, Authentication authentication, Model model) {
    	ModelAndView modelAndView = new ModelAndView("bets/personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 3);
    	Page<Bet> betPage = betService.findByOwnerAndStateActive(userService.findUserByNickname(authentication.getName()), pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        return modelAndView;
    }

    @GetMapping("/bet/user/created/ended/{page}")
    public ModelAndView personnalBetsEnded(@PathVariable("page") int page, Authentication authentication, Model model) {
    	ModelAndView modelAndView = new ModelAndView("bets/personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 3);
    	Page<Bet> betPage = betService.findByOwnerAndStateEnded(userService.findUserByNickname(authentication.getName()), pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        return modelAndView;
    }

    @GetMapping("/bet/user/created/closed/{page}")
    public ModelAndView personnalBetsClosed(@PathVariable("page") int page, Authentication authentication, Model model) {
    	ModelAndView modelAndView = new ModelAndView("bets/personnal-bets");
    	PageRequest pageable = PageRequest.of(page - 1, 3);
    	Page<Bet> betPage = betService.findByOwnerAndStateClosed(userService.findUserByNickname(authentication.getName()), pageable);
    	int totalPages = betPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("betList", betPage.getContent());
        return modelAndView;
    }
}
