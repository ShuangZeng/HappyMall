package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;

@Controller
@SessionAttributes({ "user" })
@RequestMapping({"/orders"})
public class OrderController {	
	@Autowired
	private OrdersService orderService;

	@Autowired
	private OrderLineService olService;

	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "/details/{id}")
	String getOrder(Model model, Authentication authentication, @PathVariable("id") int orderId) {
		User user = (User) model.asMap().get("user");		
		if (user == null) {
			return "orderDetail";	
		}

		// Retrieve data
		try {
			Orders order = orderService.getOrder(orderId);
					
			// Model-mapping
			
			List<OrderLine> orderLines;
			// Get order lines based on user role
			if (user.getRole().getId() == 1) {
				// User role = Enduser 
				// -> Get all order lines
				orderLines = olService.findByOrdersId(order.getId());
			} else {
				// User role = Vendor 
				// -> Get order lines only belongs to vnedor's product
				orderLines = olService.findByOrdersId(order.getId()).stream()
										.filter(ol -> ol.getProduct().getVendor().getId() == user.getId())
										.collect(Collectors.toList());
			}
			if (orderLines == null) orderLines = new ArrayList<OrderLine>();
			
			// Get billing address
			Address billing = order.getBillingAddress();
			if (billing == null) billing = new Address();
			
			// Get shipping address
			Address shipping = order.getShippingAddress();
			if (shipping == null) shipping = new Address();
			
			// Map data to view model
			model.addAttribute("orderId", order.getId());
			model.addAttribute("orderCode", order.getOrderCode());
			model.addAttribute("status", order.getStatus());
			model.addAttribute("subtotal", order.getSubTotal());
			model.addAttribute("serviceFee", order.getServiceFee());
			model.addAttribute("tax", order.getTax());
			model.addAttribute("total", order.getTotal());
			model.addAttribute("orderLines", orderLines);
			model.addAttribute("billing", billing);
			model.addAttribute("shipping", shipping);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "orderDetail";
	}

	@RequestMapping(value = "/vendor")
	public String getAllOrdersForVendor(Model model, Authentication authentication) {
		List<Orders> list = null;
		
		try {
			User user = (User) model.asMap().get("user");
			list = orderService.getAllOrdersByUser(user.getId(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("ordersList", list);
		return "orderMgmt";
	}

	@RequestMapping(value = "/user")
	public String getAllOrdersForEnduser(Model model, Authentication authentication) {
		List<Orders> list = null;
		
		try {
			User user = (User) model.asMap().get("user");
			System.out.println("USER ID IS: "+ user.getId());
			list = orderService.getAllOrdersByUser(user.getId(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("ordersList", list);
		return "orderMgmt";
	}

	// -----------------------------------------------------------------------------------------
	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void updateOrder(Orders order) {
		try {
			orderService.updateOrder(order);									
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/acceptToRefund/{id}")
	String acceptToRefund(Model model, Authentication authentication, @PathVariable("id") int orderId) {
		try {
			//orderService.requestToRefundOrder(orderId);									
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "orderRefund";
	}

	@RequestMapping(value = "/requestToRefund/{id}")
	String requestToRefund(Model model, Authentication authentication, @PathVariable("id") int orderId) {
		try {
			orderService.requestToRefundOrder(orderId);									
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "orderRefund";
	}

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Private functions/methods----------------------------------------------------------------
	// All private function/methods will be declared here
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// End Private functions/methods------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}