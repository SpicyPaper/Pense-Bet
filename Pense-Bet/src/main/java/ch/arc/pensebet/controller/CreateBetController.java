package ch.arc.pensebet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateBetController {
	@GetMapping("/bet/create")
	public String createBet()
	{
		return "create-bet";
	}
	
}
