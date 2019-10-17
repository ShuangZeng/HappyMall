package com.example.HappyMall.controller;
import java.security.Principal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	UserService userService;

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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user",user);
		modelAndView.setViewName("home"); 
		return modelAndView;
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET) 
	public ModelAndView adminHome(){ 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); 
		return modelAndView; 
		
	}
}
