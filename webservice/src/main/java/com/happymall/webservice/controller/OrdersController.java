package com.happymall.webservice.controller;

import java.util.List;

import javax.mail.MessagingException;

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
import com.happymall.webservice.service.EmailService;
import com.happymall.webservice.service.OrderService;

@RestController
@RequestMapping({"/orders"})
public class OrdersController {

	@Autowired
	OrderService os;
	
	@Autowired
	EmailService es;

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@GetMapping(value = "/addNew")
	public @ResponseBody Orders processAddNewOrder(@RequestBody Orders orderToBeAdded) {
		os.addOrder(orderToBeAdded);
		return orderToBeAdded;
	}

	@GetMapping(value = "/addNewWithNotification")
	public @ResponseBody Orders processAddNewOrderWithNotification(@RequestBody Orders orderToBeAdded) {
		try {
			os.addOrder(orderToBeAdded);
			es.notifyBuyerOfPurchase(orderToBeAdded);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderToBeAdded;
	}

	@GetMapping(value = "/sendNotification")
	public @ResponseBody Orders processSendNotification(@RequestBody Orders order) {
		try {
			es.notifyBuyerOfPurchase(order);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
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
	public List<Orders> getAllOrdersByUser(@RequestParam("id") int userId, @RequestParam("isEnduser") boolean isEnduser) {
		List<Orders> list = os.getAllOrdersByUser(userId, isEnduser);
		System.out.println("ORDERS ARE: \n ");
		list.forEach(o -> System.out.println(o));
		return list;
	}

	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@GetMapping(value = "/update")
	public void processUpdateOrder(@RequestBody Orders orderToBeUpdated) {
		os.updateOrder(orderToBeUpdated);
	}

	@GetMapping(value = "/refund")
	public void processRefundOrder(@RequestBody Orders orderToBeUpdated, @RequestBody int userId,@RequestBody boolean isEnduser) {
		os.refundOrder(orderToBeUpdated, userId, isEnduser);
	}

	@GetMapping(value = "/requestToRefund/{id}")
	public Orders requestToRefundOrder(@PathVariable("id") int orderId) {
		Orders orderToBeRefunded = os.getOrder(orderId);
		orderToBeRefunded.setStatus("Pending To Refund");
		os.updateOrder(orderToBeRefunded);
		
		return orderToBeRefunded;
	}

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

		
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@GetMapping(value = "/delete")
	public void processDeleteOrder(@RequestBody Orders orderToBeDeleted) {
		orderToBeDeleted.setStatus("Delete");
		os.updateOrder(orderToBeDeleted);
	}

	// End Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}