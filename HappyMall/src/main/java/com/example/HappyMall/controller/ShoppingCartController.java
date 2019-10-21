package com.example.HappyMall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.HappyMall.domain.*;
import com.example.HappyMall.service.*;

@Controller
@SessionAttributes({"user", "listItem", "orders"})
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

	@GetMapping("/shoppingcart")
	public String getShoppingCart(Model model, HttpSession session)
	{
		System.out.println("Load shopping cart..." );
		double subTotalGuest = 0;
		double taxGuest = 0;
		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			System.out.println("Not log in yet..." );
			List<Item> listItem = getListItemForGuest(model);
			model.addAttribute("totalProduct", listItem.size());
			model.addAttribute("listItem", listItem);
			subTotalGuest = listItem.size() > 0 ? listItem.stream().map(i -> i.getProduct().getPrice() * i.getQuantity()).reduce(0.00, Double::sum) : 0.00;
			taxGuest = subTotalGuest * 0.07;
			model.addAttribute("subTotalGuest", subTotalGuest);
			model.addAttribute("taxGuest", taxGuest);
			model.addAttribute("totalGuest", subTotalGuest + taxGuest);
		}
		else
		{
			System.out.println("User: " + user.getEmail() );
			Orders orders = null;
			List<Orders> listOrders = ordersService.findByStatusAndUserId("New", user.getId());
			if (listOrders != null  && listOrders.size() > 0)
				orders = listOrders.get(0);
			System.out.println("check error" );
			System.out.println("Orders: " + orders);
			List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
			if(listOrderLine == null)
				listOrderLine = new ArrayList<OrderLine>();
			System.out.println("orderLine size: " + listOrderLine.size());
			
			model.addAttribute("orders", orders);
			model.addAttribute("listOrderLine", listOrderLine);
			model.addAttribute("listAddress", addressService.findByUserId(user.getId()));
			model.addAttribute("listCardDetail", cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A'));
			model.addAttribute("totalProduct", listOrderLine.size());
			model.addAttribute("cardDetail", cardDetailService.getCardDefaultByUserId(user.getId()));
		}
		model.addAttribute("newAddress", new Address()); //Use for create new Address
		model.addAttribute("newCard", new CardDetail()); //Use for create new CardDetail
		return "shoppingcart";
	}
	
	private List<Item> getListItemForGuest(Model model)
	{
		System.out.println("getListItemForGuest...");
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");		
		if (listItem == null)
			listItem = new ArrayList<Item>();
		System.out.println("Finish getListItemForGuest...");
		return listItem;
	}
	
	@GetMapping("/shoppingcart/addproduct/{id:\\d+}")
	public String addToCart(@PathVariable int id, Model model, HttpSession session)
	{
		System.out.println("add to cart...");		

		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			System.out.println("Not log in yet..." );
			addToCartByGuest(id, model);
		}
		else
		{
			System.out.println("User: " + user.getEmail());	
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			//create new Order if it does not exist
			if (orders == null)
			{
				Address address = addressService.getAddressDefaultByUserId(user.getId());
				orders = new Orders(user, String.valueOf(Math.random()), address, address, "New");
			}
			//Process for Order_line
			addToCartByEndUser(id, user, orders);

			System.out.println("Complete addToCartByEndUser");	
		}

		System.out.println("finish...");
		return "redirect:/products/admin/products";
	}	
	
	private void addToCartByGuest(int id, Model model)
	{
		List<Item> listItem = (List<Item>) model.asMap().get("listItem");
		if (productService.getProduct(id) != null) 
		{
			if (listItem == null)
			{
				System.out.println("create new cart...");
				System.out.println(productService.getProduct(id));
				listItem = new ArrayList<Item>();
				if (productService.getProduct(id) != null)
					listItem.add(new Item(productService.getProduct(id), 1));
			}
			else
			{
				System.out.println("has cart in session...");
				int index = isExistItem(id, listItem);
				if (index == -1)
				{
					System.out.println("insert item into cart...");
					listItem.add(new Item(productService.getProduct(id), 1));
				}
				else
				{
					System.out.println("update quantity of item in cart...");
					int quantity = listItem.get(index).getQuantity();
					listItem.get(index).setQuantity(quantity + 1);
				}
			}
		}
	}
	
	private int isExistItem (int id, List<Item> listItem)
	{
		for (int i = 0; i < listItem.size(); i++)
		{
			if (listItem.get(i).getProduct().getId() == id)
				return i;
		}
		return -1;
	}

	private void addToCartByEndUser(int id, User user, Orders orders)
	{
		System.out.println("addToCartByEndUser...");	
		List<OrderLine> listOrderLine = orderLineService.findByOrdersId(orders.getId());
		
		if(listOrderLine == null)
			listOrderLine = new ArrayList<OrderLine>();

		OrderLine orderLine = orderLineService.getByOrderIdAndProductId(orders.getId(), id);
		if(orderLine == null)
		{
			System.out.println("create new orderLine...");	
			Product product = productService.getProduct(id);
			orderLine = new OrderLine(orders, product, product.getPrice(), 1);
		}
		else
		{
			System.out.println("update quantity for orderLine...");	
			int quantity = orderLine.getQuantity();
			orderLine.setQuantity(quantity + 1);
			orderLine.setTotal((quantity + 1) * orderLine.getPrice()); 
		}

		System.out.println("save orderLine...");	
		orderLineService.save(orderLine);
		System.out.println("updateMoneyByOrdersId...");	
		ordersService.updateMoneyByOrdersId(orders.getId());
		listOrderLine.add(orderLine);
	}
	
	@GetMapping("/shoppingcart/remove/{id:\\d+}")
	public String removeFromCart(@PathVariable int id, Model model, HttpSession session)
	{
		System.out.println("remove from cart...");
		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			System.out.println("Not log in yet..." );
			List<Item> listItem = (List<Item>) model.asMap().get("listItem");
			int index = isExistItem(id, listItem);
			if (index > -1)
				listItem.remove(index);
		}
		else
		{
			System.out.println("User: " + user.getEmail());
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
			orderLineService.deleteByOrdersIdAndProductId(orders.getId(), id);

			ordersService.updateMoneyByOrdersId(orders.getId());
			orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
		}
		
		return "redirect:/shoppingcart";
	}
	
	@PostMapping("/orders/editshippingaddress")
	public String editShippingAddress(@ModelAttribute("orders") Orders orders)
	{
		Address address = addressService.getAddress(orders.getShippingAddress().getId());
		orders.setShippingAddress(address);
		ordersService.save(orders);
		return "redirect:/shoppingcart";
	}
	
	@PostMapping("/orders/editbillingaddress")
	public String editBillingAddress(@ModelAttribute("orders") Orders orders)
	{
		Address address = addressService.getAddress(orders.getShippingAddress().getId());
		orders.setBillingAddress(address);
		ordersService.save(orders);
		return "redirect:/shoppingcart";
	}
	
	@PostMapping("/shoppingcart/setcarddefault")
	public String setDefautCard(@ModelAttribute("cardDetail") CardDetail cardDetail, Model model)
	{
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

	@PostMapping("/shoppingcart/createcard")
	public String createCard(@ModelAttribute("newCard") CardDetail cardDetail, Model model)
	{
		System.out.println("Create card detail");
		User user = (User) model.asMap().get("user");
		List<CardDetail> listCard = cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A');
		for (CardDetail item : listCard)
			item.setDefault_card(false);
		cardDetail.setDefault_card(true);
		listCard.add(cardDetail);
		cardDetailService.saveAll(listCard);
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/createaddress")
	public String createAddress(@ModelAttribute("newAddress") Address newAddress, Model model)
	{
		System.out.println("Create address");
		System.out.println("New address: " + newAddress);
		Orders orders = (Orders) model.asMap().get("orders");
		newAddress.setDefault_addr(false);
		orders.setShippingAddress(newAddress);
		addressService.save(newAddress);
		ordersService.save(orders);
		return "redirect:/shoppingcart";
	}
	
	@PostMapping("/shoppingcart")
	public String goToConfirmPage()
	{
		return "redirect:/shoppingcart/confirm";
	}
}
