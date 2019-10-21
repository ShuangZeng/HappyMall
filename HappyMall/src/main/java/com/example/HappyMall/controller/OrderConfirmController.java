package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.Payment;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.CardDetailService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.PaymentService;

@Controller
@SessionAttributes({"user", "orders"})
public class OrderConfirmController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderLineService orderLineService;
	
	@Autowired
	private CardDetailService cardDetailService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/shoppingcart/confirm")
	public String getOrderConfirmPage(Model model)
	{
		System.out.println("Load Order confirm page..." );
		User user = (User) model.asMap().get("user");
		System.out.println("User: " + user.getEmail() );
		Orders orders = null;
		List<Orders> listOrders = ordersService.findByStatusAndUserId("New", user.getId());
		if (listOrders != null)
			orders = listOrders.get(0);
		System.out.println("Orders: " + orders);
		List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
		if(listOrderLine == null)
			listOrderLine = new ArrayList<OrderLine>();
		System.out.println("orderLine size: " + listOrderLine.size());
		
		model.addAttribute("orders", orders);
		model.addAttribute("listOrderLine", listOrderLine);
		model.addAttribute("totalProduct", listOrderLine.size());
		model.addAttribute("cardDetail", cardDetailService.getCardDefaultByUserId(user.getId()));

		return "orderconfirm";
	}
	
	@PostMapping("/shoppingcart/confirm")
	public String confirmOrder(Model model)
	{
		System.out.println("Create a payment");
		Orders orders = (Orders) model.asMap().get("orders");
		System.out.println("Order: " + orders);		
		User user = (User) model.asMap().get("user");
		Payment payment = new Payment();
		payment.setOrders(orders);
		payment.setStatus("Pending");
		payment.setPaymentTotal(orders.getTotal());
		payment.setCardDetail(cardDetailService.getCardDefaultByUserId(user.getId()));
		paymentService.save(payment);
		orders.setStatus("Completed");
		ordersService.save(orders);
		System.out.println("Finish a payment");
		
		//Create new order for user with the order's status is "New"
		System.out.println("Create a new order");
		Address address = addressService.getAddressDefaultByUserId(user.getId());
		Orders newOrder = new Orders(user, String.valueOf(Math.random()), address, address, "New");
		ordersService.save(newOrder);
		System.out.println("Finish a new order");

		model.addAttribute("orders", newOrder);
		return "redirect:/products/admin/products";
	}
}
