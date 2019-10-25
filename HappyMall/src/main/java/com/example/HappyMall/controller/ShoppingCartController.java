package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.HappyMall.domain.*;
import com.example.HappyMall.service.*;

@Controller
@SessionAttributes({ "user", "listItem"})
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

	@Autowired
	private SystemConfigService systemConfigService;

	@GetMapping("/shoppingcart")
	public String getShoppingCart(Model model, HttpSession session) {
		System.out.println("Load shopping cart...");
		double subTotalGuest = 0;
		double taxGuest = 0;
		User user = (User) model.asMap().get("user");
		SystemConfig systemConfig = systemConfigService.getToApplied();
		if (user == null) {
			System.out.println("Not log in yet...");
			List<Item> listItem = getListItemForGuest(model);
			model.addAttribute("totalProduct", listItem.size());
			model.addAttribute("listItem", listItem);
			subTotalGuest = listItem.size() > 0
					? listItem.stream().map(i -> i.getProduct().getPrice() * i.getQuantity()).reduce(0.00, Double::sum)
					: 0.00;
			taxGuest = subTotalGuest * systemConfig.getTax() / 100;
			model.addAttribute("subTotalGuest", subTotalGuest);
			model.addAttribute("taxGuest", taxGuest);
			model.addAttribute("totalGuest", subTotalGuest + taxGuest);
		} else {
			System.out.println("User: " + user.getEmail());
			Orders orders = null;
			List<Orders> listOrders = ordersService.findByStatusAndUserId("New", user.getId());
			if (listOrders != null && listOrders.size() > 0)
				orders = listOrders.get(0);
			if(orders == null)
			{
				System.out.println("create orders...");
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
				ordersService.save(orders);
				System.out.println("complete creating orders...");
			}
			System.out.println("check error");
			System.out.println("Orders: " + orders);
			List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
			if (listOrderLine == null)
				listOrderLine = new ArrayList<OrderLine>();
			System.out.println("orderLine size: " + listOrderLine.size());

			model.addAttribute("orders", orders);
			model.addAttribute("listOrderLine", listOrderLine);
			model.addAttribute("listAddress", addressService.findByUserId(user.getId()));
			model.addAttribute("listCardDetail", cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A'));
			model.addAttribute("totalProduct", listOrderLine.size());
			model.addAttribute("cardDetail", cardDetailService.getCardDefaultByUserId(user.getId()));
		}
		model.addAttribute("newAddress", new Address()); // Use for create new Address
		model.addAttribute("newCard", new CardDetail()); // Use for create new CardDetail
		return "shoppingcart";
	}

	private List<Item> getListItemForGuest(Model model) {
		System.out.println("getListItemForGuest...");
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (listItem == null)
			listItem = new ArrayList<Item>();
		System.out.println("Finish getListItemForGuest...");
		return listItem;
	}

	@GetMapping("/shoppingcart/addproduct/{id:\\d+}")
	public String addToCart(@PathVariable int id, Model model, HttpSession session) {
		System.out.println("add to cart...");

		User user = (User) model.asMap().get("user");
		if (user == null) {
			System.out.println("Not log in yet...");
			addToCartByGuest(id, model);
		} else {
			System.out.println("User: " + user.getEmail());
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			// create new Order if it does not exist
			if (orders == null) {
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
			}
			// Process for Order_line
			addToCartByEndUser(id, user, orders);

			System.out.println("Complete addToCartByEndUser");
		}

		System.out.println("finish...");
		return "redirect:/index";
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

	@GetMapping("/shoppingcart/remove/{id:\\d+}")
	public String removeFromCart(@PathVariable int id, Model model, HttpSession session) {
		System.out.println("remove from cart...");
		User user = (User) model.asMap().get("user");
		if (user == null) {
			System.out.println("Not log in yet...");
			List<Item> listItem = (List<Item>) model.asMap().get("listItem");
			int index = isExistItem(id, listItem);
			if (index > -1)
				listItem.remove(index);
		} else {
			System.out.println("User: " + user.getEmail());
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			orderLineService.deleteByOrdersIdAndProductId(orders.getId(), id);
			SystemConfig systemConfig = systemConfigService.getToApplied();
			ordersService.updateMoneyByOrdersId(orders.getId(), systemConfig.getTax(), systemConfig.getServiceFee());
			orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
		}

		return "redirect:/shoppingcart";
	}

	@PostMapping("/orders/editshippingaddress")
	public String editShippingAddress(@ModelAttribute("orders") Orders orders) {
		Address address = addressService.getAddress(orders.getShippingAddress().getId());
		orders.setShippingAddress(address);
		ordersService.save(orders);
		return "redirect:/shoppingcart";
	}

	@PostMapping("/orders/editbillingaddress")
	public String editBillingAddress(@ModelAttribute("orders") Orders orders) {
		Address address = addressService.getAddress(orders.getShippingAddress().getId());
		orders.setBillingAddress(address);
		ordersService.save(orders);
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/setcarddefault")
	public String setDefautCard(@ModelAttribute("cardDetail") CardDetail cardDetail, Model model) {
		User user = (User) model.asMap().get("user");
		List<CardDetail> listCard = cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A');
		for (CardDetail card : listCard)
			if (card.getId() == cardDetail.getId())
				card.setDefault_card(true);
			else
				card.setDefault_card(false);
		cardDetailService.saveAll(listCard);
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/createCardDetail")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createCard(@Valid @RequestBody CardDetail cardDetail, Model model, BindingResult result) {
		if (!result.hasErrors()) {
			System.out.println("Create card detail");
			User user = (User) model.asMap().get("user");
			Address address = cardDetail.getAddress();
			addressService.save(address);
			List<CardDetail> listCard = cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A');
			for (CardDetail item : listCard)
				item.setDefault_card(false);
			cardDetail.setDefault_card(true);
			cardDetail.setAddress(address);
			cardDetail.setUser(user);
			cardDetail.setCreateDate(new Date());
			cardDetail.setActive_Ind('A');
			listCard.add(cardDetail);
			cardDetailService.saveAll(listCard);
			System.out.println("Card has been created");
		} else {
			System.out.println("Sorry, an error has occur. Card has not been created.");
		}
		//return "redirect:/shoppingcart";
	}

	@PostMapping(value = "/shoppingcart/createShippingAddress")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createShippingAddress(@RequestBody Address newAddress, Model model, BindingResult result) {
		System.out.println("Create Shipping address");
		if (!result.hasErrors()) {
			System.out.println("Create address");
			System.out.println("New address: " + newAddress);
			User user = (User) model.asMap().get("user");
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			newAddress.setCreateDate(new Date());
			newAddress.setUser(orders.getUser());
			addressService.save(newAddress);
			orders.setShippingAddress(newAddress);
			ordersService.save(orders);
			System.out.println("Address has been created");
		} else {
			System.out.println("Sorry, an error has occur. Address has not been created.");
		}
		//return "redirect:/shoppingcart";
	}

	@PostMapping(value = "/shoppingcart/createBillingAddress")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createBillingAddress(@RequestBody Address newAddress, Model model, BindingResult result) {
		System.out.println("Create Billing address");
		if (!result.hasErrors()) {
			System.out.println("Create address");
			System.out.println("New address: " + newAddress);
			User user = (User) model.asMap().get("user");
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			newAddress.setCreateDate(new Date());
			newAddress.setUser(orders.getUser());
			addressService.save(newAddress);
			orders.setBillingAddress(newAddress);
			ordersService.save(orders);
			System.out.println("Address has been created");
		} else {
			System.out.println("Sorry, an error has occur. Address has not been created.");
		}
		//return "redirect:/shoppingcart";

	}

	@PostMapping("/shoppingcart/updateQuantity/guest/{productId}/{quantity}")
	public @ResponseBody Orders updateQuantityGuest(@PathVariable(value = "productId") int productId, @PathVariable(value = "quantity") int quantity, Model model) 
	{
		System.out.println("Update quantity: type: guest, productId: " + productId + ", quantity: " + quantity);
		Orders order = new Orders();
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		Product product = productService.getProduct(productId);
		if (product != null && listItem != null && listItem.size() > 0) {
			int index = isExistItem(productId, listItem);
			if (index > -1) {
				System.out.println("Check quantity...");
				if (product.getQuantity() >= quantity)
				{
					System.out.println("update quantity of item in cart...");
					listItem.get(index).setQuantity(quantity);
				}
				System.out.println("Get order to return for json...");
				SystemConfig systemConfig = systemConfigService.getToApplied();
				double subTotalGuest = listItem.size() > 0 ? listItem.stream()
						.map(i -> i.getProduct().getPrice() * i.getQuantity()).reduce(0.00, Double::sum) : 0.00;
				double taxGuest = subTotalGuest * systemConfig.getTax() / 100;
				order.setSubTotal(subTotalGuest);
				order.setTax(taxGuest);
				order.setTotal(subTotalGuest + taxGuest);
			}
		}
		return order;
	}
	
	@RequestMapping(value="/shoppingcart/updateQuantity/enduser/{orderLineId}/{quantity}", method=RequestMethod.PUT)
	public @ResponseBody Orders updateQuantityEndUser(@PathVariable(value = "orderLineId") int orderLineId, @PathVariable(value = "quantity") int quantity, Model model) 
	{
		System.out.println("Update quantity: type: guest, productId: " + orderLineId + ", quantity: " + quantity);
		OrderLine orderLine = orderLineService.getOrderLine(orderLineId);
		
		if (orderLine != null)
		{
			Orders order = orderLine.getOrders();
			System.out.println("Check quantity...");
			if (orderLine.getProduct().getQuantity() >= quantity)
			{
				orderLine.setQuantity(quantity);
				orderLine.setTotal(orderLine.getPrice() * quantity);
				orderLineService.save(orderLine);
				System.out.println("Order update:" + order);
				SystemConfig systemConfig = systemConfigService.getToApplied();
				ordersService.updateMoneyByOrdersId(order.getId(), systemConfig.getTax(), systemConfig.getServiceFee());
				ordersService.save(order);
			}

			return order;
		}
		else
		{
			return new Orders();
		}
	}

	@PostMapping("/shoppingcart")
	public String goToConfirmPage() {
		return "redirect:/shoppingcart/confirm";
	}
}
