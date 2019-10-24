package com.example.HappyMall.controller;

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
import com.example.HappyMall.service.OrdersService;

@Controller
@SessionAttributes({ "user" })
@RequestMapping({"/orders"})
public class OrderController {

	@Autowired
	private OrdersService orderService;

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
			OrderModel item = new OrderModel();
			item.id = order.getId();
			item.orderCode = order.getOrderCode();
			item.status = order.getStatus();
			item.subtotal = order.getSubTotal();
			item.serviceFee = order.getServiceFee();
			item.tax = order.getTax();
			item.total = order.getTotal();
			
			if (user.getRole().getId() == 1) {
				// User role = Enduser 
				// -> Get all order lines
				item.productsList = mapOrderLines(order.getListOrderLine());
			} else {
				// User role = Vendor 
				// -> Get order lines only belongs to vnedor's product
				item.productsList = mapOrderLines(order.getListOrderLine().stream()
														.filter(ol -> ol.getProduct().getVendor().getId() == user.getId())
														.collect(Collectors.toList()));
			}
			
			Address billing = order.getBillingAddress();
			item.billing = billing != null ? mapAddress(billing) : new AddressModel();
			
			Address shipping = order.getShippingAddress();
			item.shipping = shipping != null ? mapAddress(shipping) : new AddressModel();

			model.addAttribute("order", item);
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
		
	}

	void refundOrder(Orders order, int userId, boolean isEnduser) {
		
	}

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Private functions/methods----------------------------------------------------------------
	// All private function/methods will be declared here
	// -----------------------------------------------------------------------------------------
	private AddressModel mapAddress(Address address) {
		AddressModel am = new AddressModel();
		am.line1 = address.getLineOne();
		am.line2 = address.getLineTwo();
		am.city = address.getCity();
		am.state = address.getState();
		am.zipcode = address.getZipcode();
		return am;
	}

	@SuppressWarnings("null")
	private List<ProductModel> mapOrderLines(List<OrderLine> listOrderLines) {
		List<ProductModel> list = null;

		for (OrderLine line : listOrderLines) {
			Product p = line.getProduct();
			ProductModel pm = new ProductModel();
			pm.productName = p.getName();
			pm.vendorName = p.getVendor().getFullName();
			pm.desc = p.getDescription();
			pm.price = line.getPrice();
			pm.quantity = line.getQuantity();
			pm.total = pm.price * pm.quantity;
			list.add(pm);
		}

		return list;
	}
	// -----------------------------------------------------------------------------------------
	// End Private functions/methods------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}

class OrderModel {
	int id;
	String orderCode;
	String status;
	List<ProductModel> productsList;
	double subtotal;
	double serviceFee;
	double tax;
	double total;
	AddressModel billing;
	AddressModel shipping;
}

class ProductModel {
	String productName;
	String vendorName;
	String desc;
	int quantity;
	double price;
	double total;
}

class AddressModel {
	String line1;
	String line2;
	String city;
	String state;
	String zipcode;
}