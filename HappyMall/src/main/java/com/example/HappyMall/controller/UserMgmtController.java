package com.example.HappyMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PostMapping(value = "/admin/usermgmt/")
	public String deleteUserById(Model model, @RequestParam String userId) 
	{
		User u = new User();
		u.setId(Integer.valueOf(userId));
		userService.deleteUserByUser(u);
//		System.out.print("aaaaaaaaaaaaa");
		return "redirect:/admin/usermgmt";
	}
	@GetMapping(value = "admin/usermgmt/block")
	public String blockUser(Model model, @RequestParam String userId){
		User u = new User();
		u.setId(Integer.valueOf(userId));
		userService.blockUser(u);
		System.out.print(userId);
		return "redirect:/admin/usermgmt";
	}
	@GetMapping(value = "admin/usermgmt/approve")
	public String approveUser(Model model, @RequestParam String userId){
		User u = new User();
		u.setId(Integer.valueOf(userId));
		userService.approveUser(u);
		System.out.print(userId);
		return "redirect:/admin/usermgmt";
	}
}
