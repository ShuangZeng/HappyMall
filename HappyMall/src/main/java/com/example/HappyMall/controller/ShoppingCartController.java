package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {

	@GetMapping("/")
	public String getShoppingCart()
	{
		return "shoppingcart";
	}
}
