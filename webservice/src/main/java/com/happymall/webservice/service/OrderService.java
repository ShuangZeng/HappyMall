package com.happymall.webservice.service;

import java.util.Date;
import java.util.List;

import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.domain.Product;

public interface OrderService {
	
	//-----------------------------------------------------------------------------------------
	//Create-----------------------------------------------------------------------------------
	//All creating order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	void addOrder(Product product);

 	//-----------------------------------------------------------------------------------------
 	//Retrieve---------------------------------------------------------------------------------
 	//All retrieving/getting order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	Orders getOrder(int id);
 	
	Orders getOrderByOrderCode(int userId, String orderCode);
	
	List<Orders> getAllOrders();
	
	List<Orders> getAllOrdersByUser(int userId);
	
	List<Orders> getAllOrdersByOrderStatus(int userId, String orderStatus, boolean forEndUserOrVendor);
	
	List<Orders> getAllOrdersByDateRange(int userId, Date from, Date to, boolean forEndUserOrVendor);

	//-----------------------------------------------------------------------------------------
	//Update-----------------------------------------------------------------------------------
	//All updating/modifying order functions will be declared here
	//-----------------------------------------------------------------------------------------
 	Orders updateOrder(Orders order);
	
 	//-----------------------------------------------------------------------------------------
 	//Delete-----------------------------------------------------------------------------------
 	//All deleting/removing order functions will be declared here
 	//-----------------------------------------------------------------------------------------
	void deleteOrder(int id);
}
