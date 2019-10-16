package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.HappyMall.domain.*;

@Controller
public class ShoppingCartController {

	@GetMapping("/shoppingcart")
	public String getShoppingCart(Model model, HttpSession session)
	{
		model.addAttribute("editAddress", new EditAddress());
		Product p1 = new Product();
		p1.setName("Product 1");
		p1.setPrice(1);
		p1.setDescription("description 1");
		
		Product p2 = new Product();
		p2.setName("Product 2");
		p2.setPrice(2);
		p2.setDescription("description 2");
		Product p3 = new Product();
		p3.setName("Product 3");
		p3.setPrice(3);
		p3.setDescription("description 3");

		List<Address> listAddress = new ArrayList<Address>();
		List<CardDetail> listCardDetail = new ArrayList<CardDetail>();
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(new Item(p1, 1));
		listItem.add(new Item(p2, 2));
		listItem.add(new Item(p3, 3));
		session.setAttribute("listItem", listItem);
		model.addAttribute("totalProduct", listItem.size());
		model.addAttribute("orders", new Orders());
		model.addAttribute("newAddress", new Address());
		model.addAttribute("listAddress", listAddress);
		model.addAttribute("listCardDetail", listCardDetail);
		return "shoppingcart";
	}
	
	@GetMapping("/cart/{id}")
	public String addCart(@PathVariable int id, Model model, HttpSession session)
	{
		if (session.getAttribute("listItem") == null)
		{
			List<Item> listItem = new ArrayList<Item>();
			listItem.add(new Item());
			session.setAttribute("listItem", listItem);
		}
		else
		{
			
		}
		
		return "redirect:/shoppingcart";
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
