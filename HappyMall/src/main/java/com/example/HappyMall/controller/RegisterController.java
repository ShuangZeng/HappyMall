package com.example.HappyMall.controller;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.RoleService;
import com.example.HappyMall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	

	
	@RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setActive_Ind('P');
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
		
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            //modelAndView.setViewName("register");
        	modelAndView.addObject("successMessage", "Please correct the errors in form! ");
        	modelMap.addAttribute("bindingResult",bindingResult);
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

	
	@GetMapping("/success")
	public String success() {
		return "saveSuccess";
	}
	

}
