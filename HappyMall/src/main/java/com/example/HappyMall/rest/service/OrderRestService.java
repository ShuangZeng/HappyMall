package com.example.HappyMall.rest.service;

import java.util.List;

import com.example.HappyMall.domain.Orders;

public interface OrderRestService {
	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void addOrder(Orders order);

	void addWithSendingEmail(Orders order);

	void sendNotification(Orders order);

	// End
	// Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	Orders getOrder(int id);

	List<Orders> getAllOrders();

	List<Orders> getAllOrdersByUser(int userId, boolean isEnduser);

	// End Retrieve
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void updateOrder(Orders order);

	void refundOrder(int orderId, int vendorId);

	Orders requestToRefundOrder(int orderId);

	// End
	// Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void deleteOrder(Orders order);

	// End
	// Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}