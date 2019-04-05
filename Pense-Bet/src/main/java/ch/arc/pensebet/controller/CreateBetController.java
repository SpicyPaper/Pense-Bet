package ch.arc.pensebet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.service.IBetService;

@Controller
public class CreateBetController {

	@Autowired
	private IBetService betService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyy-MM-dd"), 
				true, 10));
	}

	@GetMapping("/bet/create")
	public String betCreation(Model model) {
		model.addAttribute("bet", new Bet());
		return "create-bet";
	}

	@PostMapping("/bet/create")
	public String createBet(@ModelAttribute Bet bet) {
		betService.saveBet(bet);
		return "index.html";
	}

}
