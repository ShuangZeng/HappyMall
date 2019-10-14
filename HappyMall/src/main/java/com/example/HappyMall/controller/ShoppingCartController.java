package com.example.HappyMall.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.HappyMall.domain.EditAddress;

@Controller
public class ShoppingCartController {

	@GetMapping("/shoppingcart")
	public String getShoppingCart(Model model)
	{
		model.addAttribute("editAddress", new EditAddress());
		return "shoppingcart";
	}
	
//	@PostMapping("/")
//	public String saveNewAddress(@ModelAttribute @Valid EditAddress editAddress, BindingResult rs, RedirectAttributes ra, Model model)
//	{
//		if (rs.hasErrors()) {
//			return "shoppingcart";
//		} else {
//			return "shoppingcart";
//		}
//	}
}
