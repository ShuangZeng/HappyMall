package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.Item;
import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.Product;
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

	@GetMapping(value = { "/", "/index" })
	public String getHome(Model model, @RequestParam(defaultValue = "1") int page) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		System.out.println(user);
		modelAndView.addObject("user", user);
		modelAndView.addObject("product", new Product());

		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (listItem != null && listItem.size() > 0 && user != null) {
			// Thao Dao - To pass the list of Item to the OrderLine for user when log in
			// successfully
			System.out.println("CHECK AND CREATE ORDERS FOR USER WHEN HAS SESSION...");
			System.out.println(model.asMap().keySet().toString());
			List<OrderLine> listOrderLine;

			// Get orders by user. If does not exist, create new orders for user
			List<Orders> listOrdersNew = ordersService.findByStatusAndUserId("ShoppingCart", user.getId());

			Orders orders = null;
			if (listOrdersNew != null && listOrdersNew.size() > 0) {
				orders = listOrdersNew.get(0);
			}

			// Create a new order
			if (orders == null) {
				System.out.println("create orders...");
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				Orders lastOrder = ordersService.findLastedOrder();
				orders = new Orders(user, "od" + (lastOrder.getId() + 1), address, address, "ShoppingCart");
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

//		System.out.println(
//				"Load list of products: " + productPageAndSortingRepository.findAll(PageRequest.of(page, 6)).getNumber());
//		model.addAttribute("productList", productPageAndSortingRepository.findAll(PageRequest.of(page, 6)));
//		model.addAttribute("currentPage", page);
		List<Product> products = productService.getAllProducts().stream().filter(p -> p.getQuantity() != 0)
				.filter(p -> !p.getStatus().equals("D")).filter(p -> !p.getStatus().equals("U"))
				.collect(Collectors.toList());
		products.forEach(p -> System.out.println(p.getImageUrl()));
		model.addAttribute("productList", products);
		return "index";
	}

	@GetMapping(value = "/index/advancedSearchProduct")
	public String advancedSearchProduct(Model model) {
		model.addAttribute("product", new Product());
		return "advancedSearchProducts";
	}

	@GetMapping(value = "/index/searchResult")
	public String searchResult(Model model, @RequestParam String productName) {
		List<Product> productList = null;
		try {
			productList = productService.getProductsByName(productName).stream().filter(p -> p.getQuantity() != 0)
					.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList());
			productList.forEach(p -> System.out.println(p.getName()));
		} catch (Exception e) {
			productList = productService.getAllProducts().stream().filter(p -> p.getQuantity() != 0)
					.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList());
		}
		model.addAttribute("productList", productList);
		return "index";
	}

	@GetMapping(value = "/index/advancedSearchResult")
	public String advancedSearchResult(Model model, @RequestParam String productName, @RequestParam String productPrice,
			@RequestParam String productVendor) {
		List<Product> productList = null;
		try {
			Predicate<Product> checkValid = p -> ((p.getQuantity() != 0) && (!p.getStatus().equals("D")));
			Predicate<Product> checkPrice = p -> ((p.getPrice() < Double.parseDouble(productPrice) + 10
					&& (p.getPrice() > Double.parseDouble(productPrice) - 10)));
			Predicate<Product> checkVendor = p -> p.getVendor().getFullName().equals(productVendor);
			productList = productService.getProductsByName(productName).stream()
					.filter(checkValid.and(checkPrice).and(checkVendor)).collect(Collectors.toList());
			productList.forEach(p -> System.out.println(p.getName()));
		} catch (Exception e) {
			productList = productService.getAllProducts().stream().filter(p -> p.getQuantity() != 0)
					.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList());
		}
		model.addAttribute("productList", productList);
		return "index";
	}

	@RequestMapping(value = "/index/addproduct/{id:\\d+}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addToCart(@PathVariable int id, Model model) {
		System.out.println("add to cart...");
		User user = (User) model.asMap().get("user");
		if (user == null) {
			System.out.println("Not log in yet...");
			addToCartByGuest(id, model);
		} else {
			System.out.println("User: " + user.getEmail());
			// Get orders by user. If does not exist, create new orders for user
			List<Orders> listOrdersNew = ordersService.findByStatusAndUserId("ShoppingCart", user.getId());
			Orders orders = null;
			if (listOrdersNew != null && listOrdersNew.size() > 0) {
				orders = listOrdersNew.get(0);
			}
			// create new Order if it does not exist
			if (orders == null) {
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				Orders lastOrder = ordersService.findLastedOrder();
				orders = new Orders(user, "od" + (lastOrder.getId() + 1), address, address, "ShoppingCart");
				ordersService.save(orders);
			}
			// Process for Order_line
			addToCartByEndUser(id, user, orders);

			System.out.println("Complete addToCartByEndUser");
		}

		System.out.println("finish...");
	}

	private void addToCartByGuest(int id, Model model) {
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (productService.getProduct(id) != null) {
			if (listItem == null) {
				System.out.println("create new cart...");
				System.out.println(productService.getProduct(id));
				listItem = new ArrayList<Item>();
				if (productService.getProduct(id) != null)
					listItem.add(new Item(productService.getProduct(id), 1));
			} else {
				System.out.println("has cart in session...");
				int index = isExistItem(id, listItem);
				if (index == -1) {
					System.out.println("insert item into cart...");
					listItem.add(new Item(productService.getProduct(id), 1));
				} else {
					System.out.println("update quantity of item in cart...");
					int quantity = listItem.get(index).getQuantity();
					listItem.get(index).setQuantity(quantity + 1);
				}
			}
		}

		model.addAttribute("listItem", listItem);
	}

	private int isExistItem(int id, List<Item> listItem) {
		for (int i = 0; i < listItem.size(); i++) {
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

	private void addToCartByEndUser(int id, User user, Orders orders) {
		System.out.println("addToCartByEndUser...");
		List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());

		if (listOrderLine == null)
			listOrderLine = new ArrayList<OrderLine>();

		OrderLine orderLine = orderLineService.getByOrderIdAndProductId(orders.getId(), id);
		if (orderLine == null) {
			System.out.println("create new orderLine...");
			Product product = productService.getProduct(id);
			orderLine = new OrderLine(orders, product, product.getPrice(), 1);
		} else {
			System.out.println("update quantity for orderLine...");
			int quantity = orderLine.getQuantity();
			orderLine.setQuantity(quantity + 1);
			orderLine.setTotal((quantity + 1) * orderLine.getPrice());
		}

		System.out.println("save orderLine...");
		orderLineService.save(orderLine);
		System.out.println("updateMoneyByOrdersId...");
		SystemConfig systemConfig = systemConfigService.getToApplied();
		ordersService.updateMoneyByOrdersId(orders.getId(), systemConfig.getTax(), systemConfig.getServiceFee());
		listOrderLine.add(orderLine);
	}

}