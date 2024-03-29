package com.example.HappyMall.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.SystemConfigService;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes({ "user", "listItem" })
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderLineService orderLineService;

	@Autowired
	private SystemConfigService systemConfigService;

//	 @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
//	 public ModelAndView login(){
//	        ModelAndView modelAndView = new ModelAndView();
//	        modelAndView.setViewName("login");
//	        return modelAndView;
//	    }

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("hidden", "true");
			return "login";
		}
		return "redirect:/index";
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedForm() {
		return "access-denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
		return modelAndView;

	}
}