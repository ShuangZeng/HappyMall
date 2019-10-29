package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.SystemConfig;

public interface OrdersService {
	// ThaoDao created and edited
	Orders findByStatus(String status);

	List<Orders> findByStatusAndUserId(String status, int userId);

	void updateMoneyByOrdersId(int orderId, int tax, int serviceFee);

	Orders updateMoneyByOrders_New(Orders orders, SystemConfig systemConfig);

	Orders findLastedOrder();
	// ________________________//

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void addOrder(Orders order);

	void addWithSendingEmail(Orders order);

	void sendNotification(Orders order);

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

	// End
	// Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	void updateOrder(Orders order);

	void refundOrder(int orderId, int vendorId);

	Orders requestToRefundOrder(int orderId);

	void save(Orders order);

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
