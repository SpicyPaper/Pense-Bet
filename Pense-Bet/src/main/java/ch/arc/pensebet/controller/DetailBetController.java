package ch.arc.pensebet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import ch.arc.pensebet.service.IParticipationService;
import ch.arc.pensebet.service.IStateService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class DetailBetController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBetService betService;
	
	@Autowired
	private IParticipationService participationService;
	
	@Autowired
	private IInvitationService invitationService;
	
	@Autowired
	private IStateService stateService;
	
	@GetMapping("/bet/{id}")
    public String detailBet(Model model, @PathVariable("id") Integer id, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();
		User user = userService.findUserByNickname(authentication.getName());
		
		if (invitedUserId(bet).contains(user.getId()))
		{
			model.addAttribute("canAnswer", true);
		}
		
		fillBetDetail(model, user, bet);
        return "bets/detail-bet";
    }
	
	private List<Integer> invitedUserId(Bet bet)
	{
		return invitationService.findAllInvitations().stream().filter(i -> i.getBet().getId() == bet.getId()).mapToInt(i -> i.getUser().getId()).boxed().collect(Collectors.toList());
	}
	
	@PostMapping("/bet/{id}")
    public String inviteUser(@ModelAttribute Invitation invitation, @PathVariable("id") Integer id, Model model, Authentication authentication) {
		Bet bet = betService.findBetById(id).get();
		bet.addInvitation(invitation);
		User user = userService.findUserByNickname(authentication.getName());
		betService.saveBet(bet);
		fillBetDetail(model, user, bet);
        return "bets/detail-bet";
    }
	
	private Model fillBetDetail(Model model, User user, Bet bet)
	{
		model.addAttribute("participations", participationService.findParticipationsByBet(bet));
		model.addAttribute("bet", bet);
		if (canAddParticipant(user, bet))
		{
			model.addAttribute("invitation", new Invitation());
			model.addAttribute("users", getInvitableUsers(userService.findAllUsers(), bet));
		}
		
		if (isBetOwner(bet, user) && isBetOver(bet) && bet.getResult() == null)
		{
			model.addAttribute("canConfirmBet", true);
		}
		
		return model;
	}
	
	private boolean canAddParticipant(User user, Bet bet)
	{
		// Check if is owner and the bet isn't over
		return isBetOwner(bet, user) && !isBetOver(bet);
	}
	
	private boolean isBetOwner(Bet bet, User user)
	{
		return user.getId() == bet.getOwner().getId();
	}
	
	private boolean isBetOver(Bet bet)
	{
		return bet.getEndingDate().compareTo(new Date()) < 0;	
	}
	
	@PostMapping("/bet/{id}/participate/accept/{agree}")
	public String participateBet(@PathVariable("id") Integer id, @PathVariable("agree") boolean agree, Authentication authentication)
	{
		Bet bet = betService.findBetById(id).get();
		User user = userService.findUserByNickname(authentication.getName());
		Participation participation = new Participation();
		participation.setBet(bet);
		participation.setUser(user);
		participation.setAgree(agree);
		bet.addParticipation(participation);
		betService.saveBet(bet);
		user.addMoney(-bet.getAmount());
		userService.saveUser(user);
		
		return "index";
	}
	
	@PostMapping("/bet/{id}/confirm/{result}")
	public String confirmBet(@PathVariable("id") Integer id, @PathVariable("result") boolean result, Authentication authentication)
	{
		Bet bet = betService.findBetById(id).get();
		User user = userService.findUserByNickname(authentication.getName());
		if (isBetOwner(bet, user))
		{
			closeBet(bet, result);
		}
		
		return "index";
	}
	
	private void closeBet(Bet bet, boolean betResult)
	{
		bet.setResult(betResult);
		bet.setState(stateService.findStateByName("CLOSED"));
		betService.saveBet(bet);
		
		int numberParticipant = bet.getParticipations().size();
		if (numberParticipant > 0)
		{
			float totalAmount = bet.getAmount() * numberParticipant;
			int numberCorrectAnswer = (int) bet.getParticipations().stream().filter(p -> p.isAgree() == betResult).count();
			float moneyPerWinner = totalAmount / numberCorrectAnswer;
			bet.getParticipations().stream().filter(p -> p.isAgree() == betResult).forEach(p -> {
				p.getUser().addMoney(moneyPerWinner);
				userService.saveUser(p.getUser());
			});
			bet.setMoneyPerWinner(moneyPerWinner);
			betService.saveBet(bet);
		}
	}
	
	
	@PostMapping("/bet/{id}/participate/refuse")
	public String refuseBet(@PathVariable("id") Integer id, Authentication authentication)
	{
		Bet bet = betService.findBetById(id).get();
		User user = userService.findUserByNickname(authentication.getName());
		
		bet.cancelInvitation(user);
		betService.saveBet(bet);
		
		return "index";
	}
	
	private List<User> getInvitableUsers(List<User> allUsers, Bet bet)
	{
		List<Invitation> invitations = invitationService.findAllInvitations();
		List<Participation> participations = participationService.findAllParticipations();
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
					break;
				}
			}
			for(Participation p : participations)
			{
				if (u.getId() == p.getUser().getId() && p.getBet().getId() == bet.getId())
				{
					userOk = false;
					break;
				}
			}
			if (userOk)
				invitableUser.add(u);
		}
		return invitableUser;
	}
}
