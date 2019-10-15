package com.happymall.webservice.service.impl;

import java.util.Date;
import java.util.List;

import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.service.OrderService;

public class OrderServiceImpl implements OrderService {

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------
	
	@Override
	public void addOrder(Orders order) {
		// TODO Auto-generated method stub

	}

	// End Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
	
	
	
 	//-----------------------------------------------------------------------------------------
 	//Retrieve---------------------------------------------------------------------------------
 	//All retrieving/getting order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	
 	//START Region: Get specific order---------------------------------------------------------
	@Override
	public Orders getOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders getOrderByOrderCode(int userId, String orderCode) {
		// TODO Auto-generated method stub
		return null;
	}
	//END Region: Get specific order-----------------------------------------------------------
	
	
	//START Region: Get list of orders---------------------------------------------------------	
	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByDateRange(int userId, Date from, Date to, boolean forEndUserOrVendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByOrderStatus(int userId, String orderStatus, boolean forEndUserOrVendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	//END Region: Get list of orders-----------------------------------------------------------
	
 	//End Retrieve-----------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------

	

	//-----------------------------------------------------------------------------------------
	//Update-----------------------------------------------------------------------------------
	//All updating/modifying order functions will be declared here
	//-----------------------------------------------------------------------------------------
	
	@Override
	public Orders updateOrder(Orders order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders refundOrder(Orders order, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

 	//End Update-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------

	
	
 	//-----------------------------------------------------------------------------------------
 	//Delete-----------------------------------------------------------------------------------
 	//All deleting/removing order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	
	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub

	}
	
 	//End Delete-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------
}