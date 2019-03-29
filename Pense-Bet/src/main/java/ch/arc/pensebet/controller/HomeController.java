package ch.arc.pensebet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
	IUserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
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
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
    	ModelAndView modelAndView = new ModelAndView();
    	User userExists = userService.findUserByNickname(user.getNickname());
    	
    	if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user", "There is already a user registered with this nickname");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
        	userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");

        }
        return modelAndView;
    }
    
    @GetMapping("/bet/create")
    public String createBet() {
        return "create-bet";
    }
    
    @GetMapping("/bet/{id}/detail")
    public String detailBet() {
        return "detail-bet";
    }
    
    @GetMapping("/bet/user")
    public String personnalBets() {
        return "personnal-bets";
    }
}
