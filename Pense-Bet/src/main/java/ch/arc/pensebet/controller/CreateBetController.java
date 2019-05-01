package ch.arc.pensebet.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IInvitationService;
import ch.arc.pensebet.service.IStateService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class CreateBetController {

	@Autowired
	private IBetService betService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStateService stateService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyy-MM-dd"), true, 10));
	}

	@GetMapping("/bet/create")
	public String betCreation(Model model) {
		model.addAttribute("bet", new Bet());
		return "bets/create-bet";
	}

	@PostMapping("/bet/create")
	public ModelAndView createBet(@ModelAttribute Bet bet, Authentication authentication, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView("/bets/create-bet");
		if (bet.getSubject().isEmpty())
		{
    		modelAndView.addObject("errorMessage", "The subject cannot be empty");
		} 
		else if (bet.getEndingDate() == null)
		{
    		modelAndView.addObject("errorMessage", "The ending date cannot be empty");
		}
		else
		{
			bet.setOwner(userService.findUserByNickname(authentication.getName()));
			bet.setState(stateService.findStateByName("ACTIVE"));
			bet.setCreationDate(new Date());
			betService.saveBet(bet);
			
			Invitation invitation = new Invitation();
			invitation.setUser(userService.findOne(bet.getOwner().getId()));
			bet.addInvitation(invitation);
			betService.saveBet(bet);
			
			modelAndView.setViewName("redirect:/bet/" + bet.getId());
		}
		
		
        return modelAndView;
	}

}
