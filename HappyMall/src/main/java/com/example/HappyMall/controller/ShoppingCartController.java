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
import com.example.HappyMall.rest.service.MockServerService;
import com.example.HappyMall.service.*;

//ThaoDao created and edited
@Controller
@SessionAttributes({ "user", "listItem" })
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

	@Autowired
	private MockServerService mockServerService;

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
			List<Orders> listOrders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId());
			if (listOrders != null && listOrders.size() > 0)
				orders = listOrders.get(0);
			if (orders == null) {
				System.out.println("create orders...");
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				Orders lastOrder = ordersService.findLastedOrder();
				orders = new Orders(user, "od" + (lastOrder.getId() + 1), address, address, "ShoppingCart");
				ordersService.save(orders);
				System.out.println("complete creating orders...");
			}
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
	@GetMapping("/shoppingcart/remove/{id:\\d+}")
	public String removeFromCart(@PathVariable int id, Model model, HttpSession session) {
		try
		{
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
				Orders orders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId()).get(0);
				orderLineService.deleteByOrdersIdAndProductId(orders.getId(), id);
				SystemConfig systemConfig = systemConfigService.getToApplied();
				ordersService.updateMoneyByOrdersId(orders.getId(), systemConfig.getTax(), systemConfig.getServiceFee());
				//orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/shoppingcart";
	}

	private int isExistItem(int id, List<Item> listItem) {
		for (int i = 0; i < listItem.size(); i++) {
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

	@PostMapping("/orders/editshippingaddress")
	public String editShippingAddress(@ModelAttribute("orders") Orders ordersUpdate, Model model) {
		try
		{
			System.out.println("shipping address: " + ordersUpdate.getShippingAddress() + " - default: " + ordersUpdate.getShippingAddress());
			System.out.println("orders: " + ordersUpdate);
	
			User user = (User) model.asMap().get("user");
			Address address = addressService.getAddress(ordersUpdate.getShippingAddress().getId());
			Orders orders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId()).get(0);
			orders.setShippingAddress(address);
			orders.setModifiedDate(new Date());
			ordersService.save(orders);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/shoppingcart";
	}

	@PostMapping("/orders/editbillingaddress")
	public String editBillingAddress(@ModelAttribute("orders") Orders ordersUpdate, Model model) {
		try
		{
			System.out.println("billing address: " + ordersUpdate.getBillingAddress() + " - default: " + ordersUpdate.getBillingAddress());
			System.out.println("orders: " + ordersUpdate);
	
			User user = (User) model.asMap().get("user");
			Address address = addressService.getAddress(ordersUpdate.getBillingAddress().getId());
			Orders orders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId()).get(0);
			orders.setBillingAddress(address);
			orders.setModifiedDate(new Date());
			ordersService.save(orders);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/setcarddefault")
	public String setDefautCard(@ModelAttribute("cardDetail") CardDetail cardDetail, Model model) {
		try
		{
			User user = (User) model.asMap().get("user");
			List<CardDetail> listCard = cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A');
			for (CardDetail card : listCard)
			{
				if (card.getId() == cardDetail.getId())
					card.setDefault_card(true);
				else
					card.setDefault_card(false);
				card.setModifiedDate(new Date());
			}
			cardDetailService.saveAll(listCard);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/createCardDetail")
	//@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody CardDetail createCard(@Valid @RequestBody CardDetail cardDetail, BindingResult result, Model model) {
		try
		{
			if (!result.hasErrors()) {
				System.out.println("Create card detail");
				//Check isValid()
				MockServer mockServer = mockServerService.findByNameOnCardAndCardNumberAndCvv(cardDetail.getNameOnCard(), cardDetail.getCardNumber(), cardDetail.getCvv());
				System.out.println("Card Detail: " + cardDetail.getNameOnCard() + "-" + cardDetail.getCardNumber() + "-" + cardDetail.getCvv() + "-" + cardDetail.getExpiredDate());
				System.out.println("mockServer: " + mockServer);
				if (mockServer != null)
				{
					User user = (User) model.asMap().get("user");
					Address address = cardDetail.getAddress();
					addressService.save(address);
					List<CardDetail> listCard = cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A');
					for (CardDetail item : listCard)
						item.setDefault_card(false);
					cardDetailService.saveAll(listCard);
					cardDetail.setDefault_card(true);
					cardDetail.setActive_Ind('A');
					cardDetail.setAddress(address);
					cardDetail.setUser(user);
					//cardDetail.setIssuedDate(mockServer.getIssuedDate());
					cardDetail.setRemainingValue(mockServer.getRemainingValue());
					cardDetail.setValue(mockServer.getValue());
					cardDetail.setCreateDate(new Date());
					System.out.println("check error");
					cardDetailService.save(cardDetail);
					System.out.println("check error2");
					//listCard.add(cardDetail);
					System.out.println("Card has been created");
					return cardDetail;
				}
				else
					return null;
			} else {
				System.out.println("Sorry, an error has occur. Card has not been created.");
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping(value = "/shoppingcart/createShippingAddress")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createShippingAddress(@RequestBody Address newAddress, BindingResult result, Model model) {
		try
		{
			System.out.println("Create Shipping address");
			if (!result.hasErrors()) {
				System.out.println("Create address");
				System.out.println("New address: " + newAddress);
				User user = (User) model.asMap().get("user");
				Orders orders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId()).get(0);
				newAddress.setCreateDate(new Date());
				newAddress.setUser(orders.getUser());
				addressService.save(newAddress);
				orders.setShippingAddress(newAddress);
				orders.setModifiedDate(new Date());
				ordersService.save(orders);
				System.out.println("Address has been created");
			} else {
				System.out.println("Sorry, an error has occur. Address has not been created.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/shoppingcart/createBillingAddress")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createBillingAddress(@RequestBody Address newAddress, BindingResult result, Model model) {
		try
		{
			System.out.println("Create Billing address");
			if (!result.hasErrors()) {
				System.out.println("Create address");
				System.out.println("New address: " + newAddress);
				User user = (User) model.asMap().get("user");
				Orders orders = ordersService.findByStatusAndUserId("ShoppingCart", user.getId()).get(0);
				newAddress.setCreateDate(new Date());
				newAddress.setUser(orders.getUser());
				addressService.save(newAddress);
				orders.setBillingAddress(newAddress);
				orders.setModifiedDate(new Date());
				ordersService.save(orders);
				System.out.println("Address has been created");
			} else {
				System.out.println("Sorry, an error has occur. Address has not been created.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@RequestMapping(value="/shoppingcart/updateQuantity/guest/{productId}/{quantity}", method=RequestMethod.PUT)
	public @ResponseBody Orders updateQuantityGuest(@PathVariable(value = "productId") int productId,
			@PathVariable(value = "quantity") int quantity, Model model) {
		try
		{
			System.out.println("Update quantity: type: guest, productId: " + productId + ", quantity: " + quantity);
			Orders order = new Orders();
			List<Item> listItem = (List<Item>) model.asMap().get("listItem");
			Product product = productService.getProduct(productId);
			if (product != null && listItem != null && listItem.size() > 0) {
				int index = isExistItem(productId, listItem);
				if (index > -1) {
					System.out.println("Check quantity...");
					if (product.getQuantity() >= quantity) {
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
		catch (Exception e)
		{
			e.printStackTrace();
			return new Orders();
		}
	}

	@RequestMapping(value = "/shoppingcart/updateQuantity/enduser/{orderLineId}/{quantity}", method = RequestMethod.PUT)
	public @ResponseBody Orders updateQuantityEndUser(@PathVariable(value = "orderLineId") int orderLineId,
			@PathVariable(value = "quantity") int quantity, Model model) {
		try
		{
			System.out.println("Update quantity: type: guest, productId: " + orderLineId + ", quantity: " + quantity);
			OrderLine orderLine = orderLineService.getOrderLine(orderLineId);
	
			if (orderLine != null) {
				System.out.println("Check quantity... Product Quantity: " + orderLine.getProduct().getQuantity());
				if (orderLine.getProduct().getQuantity() >= quantity) {
					orderLine.setQuantity(quantity);
					orderLine.setTotal(orderLine.getPrice() * quantity);
					orderLineService.save(orderLine);
					System.out.println("Order update:" + orderLine.getOrders());
					//ordersService.updateMoneyByOrdersId(orderLine.getOrders().getId(), systemConfig.getTax(), systemConfig.getServiceFee());
				}
				SystemConfig systemConfig = systemConfigService.getToApplied();
				Orders orders = ordersService.updateMoneyByOrders_New(orderLine.getOrders(), systemConfig);
				System.out.println("Order after update quantity: " + orders);
				return orders;
			} else {
				return new Orders();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Orders();
		}
	}

	@PostMapping("/shoppingcart")
	public String goToConfirmPage() {
		return "redirect:/shoppingcart/confirm";
	}
}
