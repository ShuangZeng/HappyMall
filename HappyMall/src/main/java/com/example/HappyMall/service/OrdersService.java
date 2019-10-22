package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.Orders;

public interface OrdersService {
	Orders findByStatus(String status);

	List<Orders> findByStatusAndUserId(String status, int userId);

	void updateMoneyByOrdersId(int orderId);

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void addOrder(Orders order);

	// End
	// Create-----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------
	
	Orders getOrder(int id);
	
	List<Orders> getAllOrders();

	List<Orders> getAllOrdersByUser(int userId, boolean isEnduser);

	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void updateOrder(Orders order);

	void refundOrder(Orders order, int userId, boolean isEnduser);

	void save(Orders orders);

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void deleteOrder(Orders order);

	// End Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}
