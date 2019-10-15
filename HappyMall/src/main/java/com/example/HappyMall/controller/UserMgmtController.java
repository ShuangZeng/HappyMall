package com.example.HappyMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.UserService;

@Controller
public class UserMgmtController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/admin/usermgmt")
	public String getUserMgmt(Model model, Authentication authentication)
	{
		List<User> users = userService.findAllUsers();
		model.addAttribute("userList",users);
		return "UserMgmt";
	}
	
}
