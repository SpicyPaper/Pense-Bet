package ch.arc.pensebet.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IUserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    
    @PostMapping("register")
    public ModelAndView createNewUser(@RequestParam(value = "passwordConfirmation", required = true) String passwordConfirmation, @Valid User user, BindingResult bindingResult) {
    	ModelAndView modelAndView = new ModelAndView();
    	User userExists = userService.findUserByNickname(user.getNickname());
    	
    	String emailRegex = ".*";
    	 
    	Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(user.getEmail());
	    
    	if (userExists != null) {
            bindingResult.rejectValue("nickname", "error.user", "There is already a user registered with this nickname");
        }

        modelAndView.setViewName("register");
        System.out.println(matcher.matches());
        if (!matcher.matches()) {
            bindingResult.rejectValue("email", "error.user", "The email is not valid");
        }
    	else if (!user.getPassword().equals(passwordConfirmation)) {
            bindingResult.rejectValue("password", "error.user", "The password and it's confirmation doesn't match, retry");
            
        } else if (bindingResult.hasErrors()) {
    		modelAndView.addObject("errorMessage", "An error occured, check you fields!");
    		
    	} else {
        	userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
        }
    	
        return modelAndView;
    }
}
