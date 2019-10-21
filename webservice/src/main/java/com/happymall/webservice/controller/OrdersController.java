package com.happymall.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.service.OrderService;

@RestController
@RequestMapping({"/orders"})
public class OrdersController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping({"","/all"})
	public List<Orders> list(Model model) {
		return  orderService.getAllOrders();
 
	}
	
 	@GetMapping("/{id}")
	public @ResponseBody Orders getProductById( @PathVariable("id") int orderId) {

		return orderService.getOrder(orderId);
 	}
 	
 	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewOrder(@RequestBody Orders orderToBeAdded ) {

 		orderService.addOrder(orderToBeAdded);
 
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Orders processUpdateOrder(@RequestBody Orders orderToBeUpdated ) {

			return orderService.updateOrder(orderToBeUpdated);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteProduct(@RequestBody Orders orderToBeDeleted) {

		orderToBeDeleted.setStatus("D");
		orderService.updateOrder(orderToBeDeleted);
	}
	
	@RequestMapping("/user")
	public List<Orders> getAllOrdersByUser(@RequestParam("id") int userId, @RequestParam("endUser") boolean forEnduser){
		
		return orderService.getAllOrdersByUser(userId, forEnduser);
	}
	
	@RequestMapping("/code")
	public Orders getOrderByOrderCode(@RequestParam("id") int userId, @RequestParam("orderCode") String orderCode, @RequestParam("endUser") boolean forEnduser){
		
		return orderService.getOrderByOrderCode(userId, orderCode, forEnduser);
	}
 	
 	

}
