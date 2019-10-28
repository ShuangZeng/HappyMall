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
import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.CardDetailService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.PaymentService;
import com.example.HappyMall.service.ProductService;
import com.example.HappyMall.service.SystemConfigService;

//ThaoDao created and edited
@Controller
@SessionAttributes({ "user"})
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

	@Autowired
	private ProductService productService;

	@Autowired
	private SystemConfigService systemConfigService;

	@GetMapping("/shoppingcart/confirm")
	public String getOrderConfirmPage(Model model) {
		try
		{
			System.out.println("Load Order confirm page...");
			User user = (User) model.asMap().get("user");
			System.out.println("User: " + user);
	
			if (user != null) {
				Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
				System.out.println("Orders: " + orders);
	
				List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
				if (listOrderLine == null)
					listOrderLine = new ArrayList<OrderLine>();
				System.out.println("orderLine size: " + listOrderLine.size());
	
				System.out.println("Check item's quantity for the list of items");
				for (int i = 0; i < listOrderLine.size(); i++) {
					Product product = listOrderLine.get(i).getProduct();
					if (product.getQuantity() == 0) {
						orderLineService.deleteByOrdersIdAndProductId(orders.getId(), product.getId());
						listOrderLine.remove(i);
					} else if (listOrderLine.get(i).getQuantity() > product.getQuantity()) {
						System.out.println("update the item's quantity if it greater than product's quantity");
						listOrderLine.get(i).setQuantity(product.getQuantity());
					}
				}
				orderLineService.saveAll(listOrderLine);
	
				SystemConfig systemConfig = systemConfigService.getToApplied();
				orders = ordersService.updateMoneyByOrders_New(orders, systemConfig);
	
				model.addAttribute("orders", orders);
				model.addAttribute("listOrderLine", listOrderLine);
				model.addAttribute("totalProduct", listOrderLine.size());
				model.addAttribute("cardDetail", cardDetailService.getCardDefaultByUserId(user.getId()));
			}
			else
			{
				model.addAttribute("orders", null);
				model.addAttribute("listOrderLine", null);
				model.addAttribute("totalProduct", 0);
				model.addAttribute("cardDetail", null);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "orderconfirm";
	}

	@PostMapping("/shoppingcart/confirm")
	public String confirmOrder(Model model) {
		Orders newOrder = null;
		try 
		{
			System.out.println("Create a payment");
			User user = (User) model.asMap().get("user");
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			System.out.println("Order: " + orders);
			Payment payment = new Payment();
			payment.setOrders(orders);
			payment.setStatus("Pending");
			payment.setPaymentTotal(orders.getTotal());
			payment.setCardDetail(cardDetailService.getCardDefaultByUserId(user.getId()));
			paymentService.save(payment);
	
			System.out.println("Update the order's status to Completed");
			orders.setStatus("Completed");
			ordersService.save(orders);
			System.out.println("Finish a payment");
	
			System.out.println("Update the product's quantity in the inventory");
			List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
			for (OrderLine orderLine : listOrderLine) {
				int itemQuantity = orderLine.getQuantity();
				Product product = orderLine.getProduct();
				product.setQuantity(product.getQuantity() - itemQuantity);
				System.out.println("Product is updated: " + product + " - Quantity update: " + itemQuantity);
				productService.updateProduct(product);
			}
	
			// Create new order for user with the order's status is "New"
			System.out.println("Create a new order");
			Address address = addressService.getAddressDefaultByUserId(user.getId());
			newOrder = new Orders(user, String.valueOf(Math.random()), address, address, "New");
			ordersService.save(newOrder);
			System.out.println("Finish a new order");
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		model.addAttribute("orders", newOrder);
		return "redirect:/index";
	}
}
