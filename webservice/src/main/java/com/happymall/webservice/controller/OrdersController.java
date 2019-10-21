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
//@RequestMapping({"/orders"})
public class OrdersController {

	@Autowired
	OrderService orderService;

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewOrder(@RequestBody Orders orderToBeAdded) {
		orderService.addOrder(orderToBeAdded);
	}

	// End
	// Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	// START Region: Get specific
	// order---------------------------------------------------------

	@GetMapping("/{id}")
	public @ResponseBody Orders getOrderById(@PathVariable("id") int orderId) {
		return orderService.getOrder(orderId);
	}

	@RequestMapping("/code")
	public Orders getOrderByOrderCode(@RequestParam("id") int userId, @RequestParam("orderCode") String orderCode, 
										@RequestParam("endUser") boolean forEnduser) {
		return orderService.getOrderByOrderCode(userId, orderCode, forEnduser);
	}

	// END Region: Get specific
	// order-----------------------------------------------------------

	// START Region: Get list of
	// orders---------------------------------------------------------

	@RequestMapping({ "", "/all" })
	public List<Orders> getAllOrders() {
		return orderService.getAllOrders();
	}

	/*
	 * List<Orders> getAllOrdersByDateRange(int userId, Date from, Date to, boolean
	 * forEnduser);
	 * 
	 * List<Orders> getAllOrdersByOrderStatus(int userId, String orderStatus,
	 * boolean forEnduser);
	 */

	@RequestMapping("/user")
	public List<Orders> getAllOrdersByUser(@RequestParam("id") int userId, @RequestParam("endUser") boolean forEnduser) {
		return orderService.getAllOrdersByUser(userId, forEnduser);
	}

	// END Region: Get list of
	// orders-----------------------------------------------------------

	// End
	// Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processUpdateOrder(@RequestBody Orders orderToBeUpdated) {
		orderService.updateOrder(orderToBeUpdated);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processRefundOrder(@RequestBody Orders orderToBeUpdated, @RequestBody int userId) {
		orderService.refundOrder(orderToBeUpdated, userId);
	}

	// End
	// Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

		
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void processDeleteOrder(@RequestBody Orders orderToBeDeleted) {
		orderToBeDeleted.setStatus("D");
		orderService.updateOrder(orderToBeDeleted);
	}

	// End
	// Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}