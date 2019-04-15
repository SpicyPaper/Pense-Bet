package ch.arc.pensebet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.service.IBetService;

@Controller
public class DetailBetController {
	
	@Autowired
	private IBetService betService;
	
	@GetMapping("/bet/{id}/detail")
    public String detailBet(Model model, @PathVariable("id") Integer id) {
		Bet bet = betService.findBetById(id).get();
		model.addAttribute("bet", bet);
        return "detail-bet";
    }
}
