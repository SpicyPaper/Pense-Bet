package ch.arc.pensebet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class PersonnalBetsController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBetService betService;

    @GetMapping("/bet/user/created/active")
    public String personnalBetsActive(Authentication authentication, Model model) {
    	model.addAttribute("bets", betService.findPersonnalActive(userService.findUserByNickname(authentication.getName()), 
				  												  new PageRequest(0, 3)));
        return "personnal-bets";
    }

    @GetMapping("/bet/user/created/ended")
    public String personnalBetsEnded(Authentication authentication, Model model) {
    	model.addAttribute("bets", betService.findPersonnalEnded(userService.findUserByNickname(authentication.getName()), 
				  												 new PageRequest(0, 3)));
        return "personnal-bets";
    }

    @GetMapping("/bet/user/created/closed")
    public String personnalBetsClosed(Authentication authentication, Model model) {
    	model.addAttribute("bets", betService.findPersonnalClosed(userService.findUserByNickname(authentication.getName()), 
				  												  new PageRequest(0, 3)));
        return "personnal-bets";
    }
}
