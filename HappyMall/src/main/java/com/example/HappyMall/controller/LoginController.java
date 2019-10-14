package com.example.HappyMall.controller;
import java.security.Principal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

//    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
//    public String login(Model model, Principal principal) {
//    	if (principal == null)
//    	{
//	    	model.addAttribute("hidden", "true");
//	    	return "login";
//    	}
//    	return "redirect:/home";
//    }
	
	
	 @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	    public ModelAndView login(){
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("login");
	        return modelAndView;
	    }
	     
    
        
    @GetMapping("/access-denied")
    public String getAccessDeniedForm() {
    	return "access-denied";
    }

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET) 
	public ModelAndView home(){ 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); 
		return modelAndView; 
		
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET) 
	public ModelAndView adminHome(){ 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); 
		return modelAndView; 
		
	}


	/*
	 * @RequestMapping(value="/admin/home", method = RequestMethod.GET) public
	 * ModelAndView home(){ ModelAndView modelAndView = new ModelAndView();
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * modelAndView.addObject("userName", "Welcome " + user.getName() + " " +
	 * user.getLastName() + " (" + user.getEmail() + ")"); modelAndView.addObject(
	 * "adminMessage","Content Available Only for Users with Admin Role");
	 * modelAndView.setViewName("admin/home"); return modelAndView; }
	 */
}
