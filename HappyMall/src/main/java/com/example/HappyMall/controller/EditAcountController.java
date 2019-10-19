package com.example.HappyMall.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes({"user"})
public class EditAcountController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/account")
	public String getAccount(Model model,HttpSession session){
		User user = (User)model.asMap().get("user");
//		model.addAttribute("user", user);
		System.out.print(user);
		return "editUser";		
	}
	@PostMapping(value = "/account/edit")
	public String editAccount(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		
		userService.saveUser(user);
		return "redirect: /account";
	}
	
}
