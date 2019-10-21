package com.example.HappyMall.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.repository.OrdersRepository;
import com.example.HappyMall.service.OrdersService;

@Service
@Transactional
public class OrderServiceImpl implements OrdersService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public Orders findByStatus(String status) {
		// TODO Auto-generated method stub
		return ordersRepository.findByStatus(status);
	}

	@Override
	public List<Orders> findByStatusAndUserId(String status, int userId) {
		// TODO Auto-generated method stub
		return ordersRepository.findByStatusAndUserId(status, userId);
	}

	@Override
	public void updateMoneyByOrdersId(int orderId) {
		// TODO Auto-generated method stub
		ordersRepository.updateMoneyByOrdersId(orderId);
	}

	

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------
	@Override
	public void addOrder(Orders order) {
		// TODO Auto-generated method stub

	}
	// End
	// Create-----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	// START Region: Get specific
	// order------------------------------------------------------------------------------------
	@Override
	public Orders getOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders getOrderByOrderCode(int userId, String orderCode, boolean forEnduser) {
		// TODO Auto-generated method stub
		return null;
	}
	// END Region: Get specific
	// order------------------------------------------------------------------------------------

	
	// START Region: Get list of
	// orders-----------------------------------------------------------------------------------
	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByDateRange(int userId, Date from, Date to, boolean forEnduser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByOrderStatus(int userId, String orderStatus, boolean forEnduser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId, boolean forEnduser) {
		// TODO Auto-generated method stub
		return null;
	}
	// END Region: Get list of
	// orders-----------------------------------------------------------------------------------

	// End
	// Retrieve---------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void updateOrder(Orders order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refundOrder(Orders order, int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		ordersRepository.save(orders);
	}
	// End
	// Update-----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub

	}

	// End
	// Delete-----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}