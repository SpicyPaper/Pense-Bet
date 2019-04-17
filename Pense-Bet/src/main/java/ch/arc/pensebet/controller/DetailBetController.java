package ch.arc.pensebet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.model.Participation;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IBetRepository;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IInvitationService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class DetailBetController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBetService betService;
	
	@Autowired
	private IBetRepository betRepository;
	
	@Autowired
	private IInvitationService invitationService;
	
	@GetMapping("/bet/{id}")
    public String detailBet(Model model, @PathVariable("id") Integer id) {
		Bet bet = betService.findBetById(id).get();
		model.addAttribute("bet", bet);
		model.addAttribute("users", getInvitableUsers(userService.findAllUsers(), bet));
		model.addAttribute("invitation", new Invitation());
        return "detail-bet";
    }
	
	@PostMapping("/bet/{id}")
    public String inviteUser(@ModelAttribute Invitation invitation, @PathVariable("id") Integer id, Model model) {
		System.out.println("INVITATION ENTER");
		Bet bet = betService.findBetById(id).get();
		bet.addInvitation(invitation);
		betService.saveBet(bet);
		model.addAttribute("bet", bet);
		model.addAttribute("users", getInvitableUsers(userService.findAllUsers(), bet));
		model.addAttribute("invitation", new Invitation());
		System.out.println("INVITATION CREATED");
        return "detail-bet";
    }
	
	@GetMapping("/bet/{id}/participate/accept/{agree}")
	public String participateBet(@PathVariable("id") Integer id, @PathVariable("agree") boolean agree, Authentication authentication)
	{
		System.out.println("PARTICIPATION");
		Bet bet = betService.findBetById(id).get();
		User user = userService.findUserByNickname(authentication.getName());
		Participation participation = new Participation();
		participation.setBet(bet);
		participation.setUser(user);
		participation.setAgree(agree);
		bet.addParticipation(participation);
		System.out.println("CONTROLLER TAILLE AVANT " + bet.getInvitations().size());
		betService.saveBet(bet);
		bet.printInvitations();
		System.out.println("CONTROLLER TAILLE APRÈS " + bet.getInvitations().size());
		System.out.println("PARTICIPATION ADDED");
		
		return "index";
	}
	
	private List<User> getInvitableUsers(List<User> allUsers, Bet bet)
	{
		List<Invitation> invitations = invitationService.findAllInvitations();
		List<User> invitableUser = new ArrayList<>();
		for(User u : allUsers)
		{
			boolean userOk = true;
			if (u.getId() == bet.getOwner().getId())
				continue;
			
			for(Invitation i : invitations)
			{
				if (u.getId() == i.getUser().getId() && i.getBet().getId() == bet.getId())
				{
					userOk = false;
				}
			}
			if (userOk)
				invitableUser.add(u);
		}
		return invitableUser;
	}
}
