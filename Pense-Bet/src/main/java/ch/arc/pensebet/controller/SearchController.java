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
		ModelAndView modelAndView = new ModelAndView("search");
		PageRequest pageable = PageRequest.of(page - 1, 15);

		User participant = userService.findUserByNickname(authentication.getName());

		if (betSubject != null && betSubject.length() > 0) {
			Page<Bet> searchPage = betService.findBySubjectAndParticipant(participant, betSubject, pageable);

			int totalPages = searchPage.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				modelAndView.addObject("pageNumbers", pageNumbers);
			}
			modelAndView.addObject("searchList", searchPage.getContent());
		} else
			modelAndView.addObject("searchList", new PageImpl<Bet>(new ArrayList<Bet>()));

		List<User> allUsers = userService.findAllUsers();
		allUsers.remove(participant);
		modelAndView.addObject("users", allUsers);

		return modelAndView;
	}

	@PostMapping("/search/{page}")
	public ModelAndView advancedSearch(@RequestParam("betSubject") String betSubject,
			@RequestParam(value = "searchedOwner", required = false) User searchedOwner, @PathVariable("page") int page,
			Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("search");
		PageRequest pageable = PageRequest.of(page - 1, 15);

		User participant = userService.findUserByNickname(authentication.getName());

		Page<Bet> searchPage = searchedOwner != null
				? betService.findBySubjectAndParticipantAndOwner(participant, betSubject, searchedOwner, pageable)
				: betService.findBySubjectAndParticipant(participant, betSubject, pageable);

		int totalPages = searchPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("searchList", searchPage.getContent());

		List<User> allUsers = userService.findAllUsers();
		allUsers.remove(participant);
		modelAndView.addObject("users", allUsers);

		return modelAndView;
	}
}
