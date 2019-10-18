package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.HappyMall.domain.*;
import com.example.HappyMall.service.*;

@Controller
@SessionAttributes({"user", "listItem"})
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
		System.out.println("Load shopping cart..." );
		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			System.out.println("Not log in yet..." );
//			List<Item> listItem = getListItemForGuest(model);
//			model.addAttribute("totalProduct", listItem.size());
//			model.addAttribute("listItem", listItem);
		}
		else
		{
			System.out.println("User: " + user.getEmail() );
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			System.out.println("Orders: " + orders);
			System.out.println("Total: " + orders.getTotal());
			System.out.println("Tax: " + orders.getTax());
			//List<OrderLine> orderLine = new ArrayList<OrderLine>();
			List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
			System.out.println("orderLine size: " + listOrderLine.size());
			
			model.addAttribute("orders", orders);
			model.addAttribute("listItem", listOrderLine);
			model.addAttribute("listAddress", addressService.findByUserId(user.getId()));//listAddress);
			model.addAttribute("listCardDetail", cardDetailService.findByUserId(user.getId()));//listCardDetail);
			model.addAttribute("totalProduct", listOrderLine == null ? 0 : listOrderLine.size());
			model.addAttribute("cardDefault", cardDetailService.getCardDefaultByUserId(user.getId()));
		}
		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
		return "shoppingcart";
	}
	
	private List<Item> getListItemForGuest(Model model)
	{
		System.out.println("getListItemForGuest...");
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		for(Item item : listItem)
		{
			System.out.println(item.getProduct().toString());
		}
		if (listItem == null)
			listItem = new ArrayList<Item>();
		System.out.println("Finish getListItemForGuest...");
		return listItem;
	}
	
	@GetMapping("/shoppingcart/{id}")
	public String addCart(@PathVariable int id, Model model, HttpSession session)
	{
		System.out.println("add to cart...");
		

		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			List<Item> listItem = addToCartByGuest(id, model);
			model.addAttribute("listItem", listItem);
			System.out.println(listItem.size());
			model.addAttribute("totalProduct", listItem.size());
		}
		else
		{
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			if (orders == null)
			{
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
			}
			
			List<OrderLine> listOrderLine = addToCartByEndUser(id, user, orders);
			
			model.addAttribute("orders", orders);
			model.addAttribute("listItem", listOrderLine);
			model.addAttribute("listAddress", addressService.findByUserId(user.getId()));//listAddress);
			model.addAttribute("listCardDetail", cardDetailService.findByUserId(user.getId()));//listCardDetail);
			model.addAttribute("totalProduct", listOrderLine == null ? 0 : listOrderLine.size());
			model.addAttribute("cardDefault", cardDetailService.getCardDefaultByUserId(user.getId()));
			
		}

		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
		System.out.println("finish...");
		return "redirect:/products/allproducts";
	}	
	
	private List<Item> addToCartByGuest(int id, Model model)
	{
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (productService.getProduct(id) != null) 
		{
			if (listItem == null)
			{
				System.out.println("create new cart...");
				System.out.println(productService.getProduct(id));
				listItem = new ArrayList<Item>();
				if (productService.getProduct(id) != null)
					listItem.add(new Item(productService.getProduct(id), 1));
			}
			else
			{
				System.out.println("has cart in session...");
				int index = isExistItem(id, listItem);
				if (index == -1)
				{
					listItem.add(new Item(productService.getProduct(id), 1));
				}
				else
				{
					int quantity = listItem.get(index).getProduct().getQuantity();
					listItem.get(index).getProduct().setQuantity(quantity + 1);
				}
			}
		}
		return listItem;
	}
	
	private int isExistItem (int id, List<Item> listItem)
	{
		for (int i = 0; i < listItem.size(); i++)
		{
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

	private List<OrderLine> addToCartByEndUser(int id, User user, Orders orders)
	{
		List<OrderLine> listItem = orderLineService.findByOrdersId(orders.getId());
		OrderLine orderLine;
		if(listItem == null)
			listItem = new ArrayList<OrderLine>();
		
		int index = isExistOrderLine (id, listItem);
		if(index == -1)
		{
			Product product = productService.getProduct(id);
			orderLine = new OrderLine(orders, product, product.getPrice(), 1);
		}
		else
		{
			orderLine = listItem.get(index);
			int quantity = orderLine.getQuantity();
			orderLine.setQuantity(quantity + 1);
			orderLine.setTotal((quantity + 1) * orderLine.getPrice()); 
		}
		
		orderLineService.save(orderLine);
		ordersService.updateMoneyByOrdersId(orders.getId());
		listItem.add(orderLine);
		
		return listItem;
	}
	
	private int isExistOrderLine (int id, List<OrderLine> listItem)
	{
		for (int i = 0; i < listItem.size(); i++)
		{
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}
}
