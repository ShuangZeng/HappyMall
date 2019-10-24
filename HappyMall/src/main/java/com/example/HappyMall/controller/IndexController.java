package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.Item;
import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.repository.ProductPageAndSortingRepository;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.OrderLineService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.ProductService;
import com.example.HappyMall.service.SystemConfigService;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes({ "user", "listItem" })
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

	@Autowired
	private ProductPageAndSortingRepository productPageAndSortingRepository;

	@GetMapping(value = "/admin/index")
	public String getHome(Model model, @RequestParam(defaultValue = "1") int page) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user",user);
		
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (listItem != null && listItem.size() > 0 && user != null) 
		{
			// Thao Dao - To pass the list of Item to the OrderLine for user when log in
			// successfully
			System.out.println("CHECK AND CREATE ORDERS FOR USER WHEN HAS SESSION...");
			System.out.println(model.asMap().keySet().toString());
			List<OrderLine> listOrderLine;

			// Get orders by user. If does not exist, create new orders for user
			List<Orders> listOrdersNew = ordersService.findByStatusAndUserId("New", user.getId());

			Orders orders = null;
			if (listOrdersNew != null && listOrdersNew.size() > 0) {
				orders = listOrdersNew.get(0);
			}

			// Create a new order
			if (orders == null) {
				System.out.println("create orders...");
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
				ordersService.save(orders);
				System.out.println("complete creating orders...");
			}

			listOrderLine = orderLineService.findByOrdersId(orders.getId());
			for (Item item : listItem) {
				OrderLine orderLine = orderLineService.getByOrderIdAndProductId(orders.getId(),
						item.getProduct().getId());
				if (orderLine == null) {
					orderLine = new OrderLine(orders, item.getProduct(), item.getProduct().getPrice(),
							item.getQuantity());
					orderLineService.save(orderLine);
				} else {
					int quantity = orderLine.getQuantity();
					orderLine.setQuantity(quantity + 1);
					orderLine.setTotal((quantity + 1) * orderLine.getPrice());
					listOrderLine.add(orderLine);
				}
			}
			System.out.println("update orders...");
			SystemConfig systemConfig = systemConfigService.getToApplied();
			ordersService.updateMoneyByOrdersId(orders.getId(), systemConfig.getTax(), systemConfig.getServiceFee());

			model.addAttribute("listItem", new ArrayList<Item>());			
		}
		// Finish
				
		System.out.println(
				"Load list of products: " + productPageAndSortingRepository.findAll(PageRequest.of(page, 5)).getNumber());
		model.addAttribute("productList", productPageAndSortingRepository.findAll(PageRequest.of(page, 5)));
		model.addAttribute("currentPage", page);

		return "index";
	}
}
