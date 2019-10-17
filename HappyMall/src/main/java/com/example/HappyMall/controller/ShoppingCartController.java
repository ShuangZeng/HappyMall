package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.HappyMall.domain.*;
import com.example.HappyMall.service.*;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CardDetailService cardDetailService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderLineService orderLineService;

	@GetMapping("/shoppingcart")
	public String getShoppingCart(Model model, HttpSession session)
	{
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
		
		model.addAttribute("totalProduct", listItem.size());

		
		Orders orders = ordersService.findByStatusAndUserId("New", 1004).get(0);
		System.out.println(orderLineService.findByOrdersId(orders.getId()).size());
		model.addAttribute("orders", orders);
		model.addAttribute("orderLine", orderLineService.findByOrdersId(orders.getId()));
		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
		model.addAttribute("listAddress", addressService.findByUserId(1004));//listAddress);
		model.addAttribute("listCardDetail", cardDetailService.findByUserId(1004));//listCardDetail);
		

		session.setAttribute("listItem", orderLineService.findByOrdersId(orders.getId()));
		return "shoppingcart";
	}
	
	@GetMapping("/shoppingcart/{id}")
	public String addCart(@PathVariable int id, Model model, HttpSession session)
	{
		System.out.println("add to cart...");
		List<Item> listItem = new ArrayList<Item>();
		if (productService.getOne(id) != null) 
		{
			if (session.getAttribute("listItem") == null)
			{
				System.out.println("create new cart...");
				System.out.println(productService.getOne(id));
				if (productService.getOne(id) != null)
					listItem.add(new Item(productService.getOne(id), 1));
			}
			else
			{
				System.out.println("has cart in session...");
				listItem = (List<Item>) session.getAttribute("listItem");
				int index = isExist(id, listItem);
				if (index == -1)
				{
					listItem.add(new Item(productService.getOne(id), 1));
				}
				else
				{
					int quantity = listItem.get(index).getProduct().getQuantity();
					listItem.get(index).getProduct().setQuantity(quantity + 1);
				}
			}
		}

		session.setAttribute("listItem", listItem);
		List<Address> listAddress = new ArrayList<Address>();
		List<CardDetail> listCardDetail = new ArrayList<CardDetail>();
		model.addAttribute("totalProduct", listItem.size());
		model.addAttribute("orders", new Orders());
		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
		model.addAttribute("listAddress", listAddress);
		model.addAttribute("listCardDetail", listCardDetail);

		System.out.println("finish...");
		return "redirect:/products/allproducts";
	}
	
	private int isExist (int id, List<Item> listItem)
	{
		for (int i = 0; i < listItem.size(); i++)
		{
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

}
