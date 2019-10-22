package com.example.HappyMall.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class OrderController {

	@Autowired
	private OrdersService orderService;

	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@GetMapping(value = "admin/vendor/orderDetail")
	String getOrderForVendor(Model model, Authentication authentication, @RequestParam int orderId) {
		try {
			// Retrieve data
			Orders order = new Orders();// orderService.getOrder(orderId);
			User user = (User) model.asMap().get("user");

			// Model-mapping
			OrderModel item = new OrderModel();
			item.id = order.getId();
			item.orderCode = order.getOrderCode();
			item.status = order.getStatus();
			item.productsList = mapOrderLines(order.getListOrderLine().stream()
					.filter(ol -> ol.getProduct().getVendor().getId() == user.getId()).collect(Collectors.toList()));
			item.subtotal = order.getSubTotal();
			item.serviceFee = order.getServiceFee();
			item.tax = order.getTax();
			item.total = order.getTotal();
			item.billing = mapAddress(order.getBillingAddress());
			item.shipping = mapAddress(order.getShippingAddress());

			model.addAttribute("order", item);
			return "orderDetail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/shoppingcart/orderDetail")
	String getOrderForEnduser(Model model, Authentication authentication, @RequestParam int orderId) {
		try {
			// Retrieve data
			Orders order = new Orders();// orderService.getOrder(orderId);
			User user = (User) model.asMap().get("user");

			// Model-mapping
			OrderModel item = new OrderModel();
			item.id = order.getId();
			item.orderCode = order.getOrderCode();
			item.status = order.getStatus();
			item.productsList = mapOrderLines(order.getListOrderLine());
			item.subtotal = order.getSubTotal();
			item.serviceFee = order.getServiceFee();
			item.tax = order.getTax();
			item.total = order.getTotal();
			item.billing = mapAddress(order.getBillingAddress());
			item.shipping = mapAddress(order.getShippingAddress());

			model.addAttribute("order", item);
			return "orderDetail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "admin/vendor/orders")
	public String getAllOrdersForVendor(Model model, Authentication authentication) {
		try {
			User user = (User) model.asMap().get("user");
			model.addAttribute("ordersList", orderService.getAllOrdersByUser(user.getId(), false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ordersList";
	}

	@GetMapping(value = "/shoppingcart/orders")
	public String getAllOrdersForEnduser(Model model, Authentication authentication) {
		try {
			User user = (User) model.asMap().get("user");
			model.addAttribute("ordersList", orderService.getAllOrdersByUser(user.getId(), true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ordersList";
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

	void refundOrder(Orders order, int vendorId) {
		
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