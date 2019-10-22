package com.happymall.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	OrderService os;

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewOrder(@RequestBody Orders orderToBeAdded) {
		os.addOrder(orderToBeAdded);
	}

	// End Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@GetMapping("/{id}")
	public @ResponseBody Orders getOrderById(@PathVariable("id") int orderId) {
		return os.getOrder(orderId);
	}

	@RequestMapping({ "", "/all" })
	public List<Orders> getAllOrders() {
		return os.getAllOrders();
	}

	@RequestMapping("/user")
	public List<Orders> getAllOrdersByUser(@RequestParam("id") int userId, @RequestParam("endUser") boolean isEnduser) {
		return os.getAllOrdersByUser(userId, isEnduser);
	}

	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processUpdateOrder(@RequestBody Orders orderToBeUpdated) {
		os.updateOrder(orderToBeUpdated);
	}

	@RequestMapping(value = "/refund", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processRefundOrder(@RequestBody Orders orderToBeUpdated, @RequestBody int userId,@RequestBody boolean isEnduser) {
		os.refundOrder(orderToBeUpdated, userId, isEnduser);
	}

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

		
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processDeleteOrder(@RequestBody Orders orderToBeDeleted) {
		orderToBeDeleted.setStatus("D");
		os.updateOrder(orderToBeDeleted);
	}

	// End Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}