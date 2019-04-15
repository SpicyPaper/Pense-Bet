package ch.arc.pensebet.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class PersonnalBetsController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBetService betService;

    @GetMapping("/bet/user")
    public String personnalBets(@AuthenticationPrincipal User activeUser, Model model) {
    	model.addAttribute("bets", betService.findAllActive(activeUser, new PageRequest(0, 3)));
        return "personnal-bets";
    }
}
