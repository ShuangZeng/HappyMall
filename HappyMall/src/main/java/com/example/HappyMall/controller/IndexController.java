package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.Item;
import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.ProductService;
import com.example.HappyMall.service.SystemConfigService;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes ({"user", "listItem"})
public class IndexController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderLineService orderLineService;

	@Autowired
	private SystemConfigService systemConfigService;
	
	@GetMapping(value = "/admin/index")
	public String getHome(Model model ) {
		User user = (User)model.asMap().get("user");
		System.out.println("list product size: " + productService.getAllProducts().size());
		model.addAttribute("productList", productService.getAllProducts());
		
		//Thao code
		System.out.println("CHECK AND CREATE ORDERS FOR USER WHEN HAS SESSION...");
		System.out.println(model.asMap().keySet().toString());
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		List<OrderLine> listOrderLine;
		Orders orders = null;
		if (listItem != null && user != null && listItem.size() > 0)
		{
			List<Orders> listOrdersNew = ordersService.findByStatusAndUserId("New", user.getId());
			if (listOrdersNew != null)
				orders = listOrdersNew.get(0);
			if (orders == null)
			{
				System.out.println("orders is null...");
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
				ordersService.save(orders);
				listOrderLine = new ArrayList<OrderLine>();
				for(Item item : listItem)
				{
					OrderLine orderLine = new OrderLine(orders, item.getProduct(), item.getProduct().getPrice(), item.getQuantity());
					orderLineService.save(orderLine);
					listOrderLine.add(orderLine);
				}
			}
			else
			{
				System.out.println("user has orders...");
				listOrderLine = orderLineService.findByOrdersId(orders.getId());
				for(Item item : listItem)
				{
					OrderLine orderLine = orderLineService.getByOrderIdAndProductId(orders.getId(), item.getProduct().getId());
					if(orderLine == null)
					{
						orderLine = new OrderLine(orders, item.getProduct(), item.getProduct().getPrice(), item.getQuantity());
    					orderLineService.save(orderLine);
					}
					else
					{
						int quantity = orderLine.getQuantity();
						orderLine.setQuantity(quantity + 1);
						orderLine.setTotal((quantity + 1) * orderLine.getPrice());
						listOrderLine.add(orderLine);
					}
				}
				System.out.println("update orders...");
				SystemConfig systemConfig = systemConfigService.getToApplied();
	    		ordersService.updateMoneyByOrdersId(orders.getId(), systemConfig.getTax(), systemConfig.getServiceFee());
			}
		}
		else
		{
			System.out.println("listItem is null");
		}
		model.addAttribute("listItem", null);
		
		return "index";
	}
}
