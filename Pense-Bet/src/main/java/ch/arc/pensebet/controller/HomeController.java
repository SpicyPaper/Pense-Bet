package ch.arc.pensebet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.arc.pensebet.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
	IUserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/bet/create")
    public String createBet() {
        return "create-bet";
    }
    
    @GetMapping("/bet/{id}/detail")
    public String detailBet() {
        return "detail-bet";
    }
    
    @GetMapping("/bet/user")
    public String personnalBets() {
        return "personnal-bets";
    }
}
