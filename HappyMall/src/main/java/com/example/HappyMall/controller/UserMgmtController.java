package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserMgmtController {
	@RequestMapping(value = "/admin/usermgmt")
	public String getShoppingCart()
	{
		return "UserMgmt";
	}
}
