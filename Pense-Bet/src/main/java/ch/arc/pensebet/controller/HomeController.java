package ch.arc.pensebet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBetService betService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/bet/user/waiting")
    public String allBetsWaiting(@AuthenticationPrincipal User activeUser, Model model) {
    	model.addAttribute("bets", betService.findPersonnalActive(activeUser, new PageRequest(0, 3)));
        return "index";
    }

    @GetMapping("/bet/user/active")
    public String allBetsActive(@AuthenticationPrincipal User activeUser, Model model) {
    	model.addAttribute("bets", betService.findPersonnalActive(activeUser, new PageRequest(0, 3)));
        return "index";
    }

    @GetMapping("/bet/user/ended")
    public String allBetsEnded(@AuthenticationPrincipal User activeUser, Model model) {
    	model.addAttribute("bets", betService.findPersonnalActive(activeUser, new PageRequest(0, 3)));
        return "index";
    }

    @GetMapping("/bet/user/closed")
    public String allBetsClosed(@AuthenticationPrincipal User activeUser, Model model) {
    	model.addAttribute("bets", betService.findPersonnalActive(activeUser, new PageRequest(0, 3)));
        return "index";
    }
}
