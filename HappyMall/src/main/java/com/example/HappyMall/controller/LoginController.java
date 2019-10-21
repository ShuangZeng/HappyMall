package com.example.HappyMall.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.HappyMall.domain.*;
import com.example.HappyMall.service.*;

@Controller
@SessionAttributes({"user", "listItem"})
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderLineService orderLineService;  
	
//	@Autowired
//    private SessionRegistry sessionRegistry;

//	 @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
//	 public ModelAndView login(){
//	        ModelAndView modelAndView = new ModelAndView();
//	        modelAndView.setViewName("login");
//	        return modelAndView;
//	    }
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
    	if (principal == null)
    	{
	    	model.addAttribute("hidden", "true");
	    	return "login";
    	}
    	return "redirect:/home";
    }

    
        
    @GetMapping("/access-denied")
    public String getAccessDeniedForm() {
    	return "access-denied";
    }

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET) 
	public ModelAndView home(Model model){ 
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user",user);

		//Thao Dao - To pass the list of Item to the OrderLine for user when log in successfully
		System.out.println("CHECK AND CREATE ORDERS FOR USER WHEN HAS SESSION...");
		System.out.println(model.asMap().keySet().toString());
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		List<OrderLine> listOrderLine;

		//Get orders by user. If does not exist, create new orders for user
		List<Orders> listOrdersNew = ordersService.findByStatusAndUserId("New", user.getId());
		Orders orders = null;
		if (listOrdersNew != null && listOrdersNew.size() > 0)
		{
			System.out.println("CHECK error1...");
			orders = listOrdersNew.get(0);
		}

		System.out.println("CHECK error2...");
        //Create a new order
		if (orders == null)
		{
			System.out.println("CHECK error3 create orders...");
			Address address = addressService.getAddressDefaultByUserId(user.getId());
			orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
			ordersService.save(orders);
			System.out.println("CHECK error3 complete creating orders...");
		}
		
		if (listItem != null && listItem.size() > 0 && user != null)
		{
			if(orders != null)
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
	    		ordersService.updateMoneyByOrdersId(orders.getId());
			}
		}
		else
		{
			System.out.println("listItem is null");
		}
		//Finish	
		model.addAttribute("listItem", new ArrayList<Item>());

		modelAndView.setViewName("home"); 
		return modelAndView;
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET) 
	public ModelAndView adminHome(){ 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); 
		return modelAndView; 
	}
}
