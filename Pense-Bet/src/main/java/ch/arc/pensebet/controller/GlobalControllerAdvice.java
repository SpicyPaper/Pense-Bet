package ch.arc.pensebet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IUserService;

@ControllerAdvice
public class GlobalControllerAdvice {
	@Autowired
	private IUserService userService;
	
    @ModelAttribute("loggedUser")
    public User populateUser() {

    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username;
		if (principal instanceof org.springframework.security.core.userdetails.User) {
		  username = ((org.springframework.security.core.userdetails.User)principal).getUsername();
		} else {
		  username = principal.toString();
		}
    	
    	return userService.findUserByNickname(username);
    }
}
