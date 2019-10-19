package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.HappyMall.domain.User;

@Controller
@SessionAttributes ({"user"})
public class AdminController {

	@RequestMapping(value = "/admin")
	public String getAdmin(Model model)
	{
		User user = (User)model.asMap().get("user");
		model.addAttribute("user", user);
		return "admin";
	}
}
