package com.happymall.webservice.service;

import java.util.List;

import com.happymall.webservice.domain.Orders;


public interface OrderService {
	
	//-----------------------------------------------------------------------------------------
	//Create-----------------------------------------------------------------------------------
	//All creating order functions will be declared here
 	//-----------------------------------------------------------------------------------------
	
 	void addOrder(Orders order);
 	
 	//End Create-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------

 	
 	
 	//-----------------------------------------------------------------------------------------
 	//Retrieve---------------------------------------------------------------------------------
 	//All retrieving/getting order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	
 	//START Region: Get specific order---------------------------------------------------------
 	Orders getOrder(int id);
 	
	Orders getOrderByOrderCode(int userId, String orderCode, boolean forEnduser);
	//END Region: Get specific order-----------------------------------------------------------
	
	
	//START Region: Get list of orders---------------------------------------------------------	
	List<Orders> getAllOrders();
	
	/*
	 * List<Orders> getAllOrdersByDateRange(int userId, Date from, Date to, boolean
	 * forEnduser);
	 * 
	 * List<Orders> getAllOrdersByOrderStatus(int userId, String orderStatus,
	 * boolean forEnduser);
	 */
	
	List<Orders> getAllOrdersByUser(int userId, boolean forEnduser);
	//END Region: Get list of orders-----------------------------------------------------------
	
 	//End Retrieve-----------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------
	
	
	
	//-----------------------------------------------------------------------------------------
	//Update-----------------------------------------------------------------------------------
	//All updating/modifying order functions will be declared here
	//-----------------------------------------------------------------------------------------
	
 	void updateOrder(Orders order);
 	
 	void refundOrder(Orders order, int userId);

 	//End Update-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------
	
 	
 	
 	//-----------------------------------------------------------------------------------------
 	//Delete-----------------------------------------------------------------------------------
 	//All deleting/removing order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	
	void deleteOrder(int id);
	
 	//End Delete-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------
}
