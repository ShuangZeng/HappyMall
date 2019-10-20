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
			model.addAttribute("disabled", true);
		}
		else
		{
			System.out.println("User: " + user.getEmail() );
			Orders orders = ordersService.findByStatusAndUserId("New", user.getId()).get(0);
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
			model.addAttribute("disabled", false);

    		model.asMap().remove("listItem");
		}
		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
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
	
	@GetMapping("/shoppingcart/{id}")
	public String addToCart(@PathVariable int id, Model model, HttpSession session)
	{
		System.out.println("add to cart...");		

		User user = (User) model.asMap().get("user");
		if(user == null)
		{
			System.out.println("Not log in yet..." );
			List<Item> listItem = addToCartByGuest(id, model);
			model.addAttribute("listItem", listItem);
			System.out.println(listItem.size());
			model.addAttribute("totalProduct", listItem.size());
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
			List<OrderLine> listOrderLine = addToCartByEndUser(id, user, orders);

			System.out.println("Complete addToCartByEndUser");	
			
			model.addAttribute("orders", orders);
			model.addAttribute("listOrderLine", listOrderLine);
			model.addAttribute("listItem", null);
			model.addAttribute("listAddress", addressService.findByUserId(user.getId()));
			model.addAttribute("listCardDetail", cardDetailService.findByUserIdAndActiveInd(user.getId(), 'A'));
			model.addAttribute("totalProduct", listOrderLine == null ? 0 : listOrderLine.size());
			model.addAttribute("cardDetail", cardDetailService.getCardDefaultByUserId(user.getId()));
			
		}

		model.addAttribute("newAddress", new Address());
		model.addAttribute("newCard", new CardDetail());
		System.out.println("finish...");
		return "redirect:/admin/products";
	}	
	
	private List<Item> addToCartByGuest(int id, Model model)
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
		return listItem;
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

	private List<OrderLine> addToCartByEndUser(int id, User user, Orders orders)
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
		
		return listOrderLine;
	}
	
	@GetMapping("/shoppingcart/remove/{id}")
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
	
	@PostMapping("/shoppingcart/setcarddefaut")
	public String setDefautCard(@ModelAttribute("cardDetail") CardDetail cardDetail)
	{
		CardDetail cardDetailUpdate = cardDetailService.getCardDetail(cardDetail.getId());
		cardDetailUpdate.setDefault_card(true);
		cardDetailService.save(cardDetailUpdate);
		return "redirect:/shoppingcart";
	}

	@PostMapping("/shoppingcart/createcard")
	public String createCard(@ModelAttribute("cardDetail") CardDetail cardDetail)
	{
		cardDetailService.save(cardDetail);
		return "redirect:/shoppingcart";
	}
}
