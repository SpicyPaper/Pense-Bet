package ch.arc.pensebet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IBetService;
import ch.arc.pensebet.service.IUserService;

@Controller
public class SearchController {

	@Autowired
	IUserService userService;

	@Autowired
	IBetService betService;

	@GetMapping("/search/{page}")
	public ModelAndView simpleSearch(@RequestParam(value = "betSubject", required = false) String betSubject,
			@PathVariable("page") int page, Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("bets/search");

		User participant = userService.findUserByNickname(authentication.getName());

		if (betSubject != null && betSubject.length() > 0) {
			List<Bet> searchPage = betService.findBySubjectAndParticipant(participant, betSubject);

			
			modelAndView.addObject("searchList", searchPage);
		} else {
			modelAndView.addObject("searchList", new ArrayList<Bet>());
			System.out.println("ok");
		}

		List<User> allUsers = userService.findAllUsers();
		modelAndView.addObject("users", allUsers);

		return modelAndView;
	}

	@PostMapping("/search/{page}")
	public ModelAndView advancedSearch(@RequestParam("betSubject") String betSubject,
			@RequestParam(value = "searchedOwner", required = false) User searchedOwner, @PathVariable("page") int page,
			Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("bets/search");
		System.out.println("ok3");

		User participant = userService.findUserByNickname(authentication.getName());

		List<Bet> searchPage = searchedOwner != null
				? betService.findBySubjectAndParticipantAndOwner(participant, betSubject, searchedOwner)
				: betService.findBySubjectAndParticipant(participant, betSubject);

		modelAndView.addObject("searchList", searchPage);

		List<User> allUsers = userService.findAllUsers();
		modelAndView.addObject("users", allUsers);

		return modelAndView;
	}
}
