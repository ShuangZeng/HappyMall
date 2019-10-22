package com.example.HappyMall.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.repository.OrdersRepository;
import com.example.HappyMall.rest.service.OrderRestService;
import com.example.HappyMall.service.OrdersService;

@Service
@Transactional
public class OrderServiceImpl implements OrdersService {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	OrderRestService ors;

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
		try {
			ors.addOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// End Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	// START Region: Get specific order
	// -----------------------------------------------------------------------------------------
	@Override
	public Orders getOrder(int id) {
		return ors.getOrder(id);
	}
	
	@Override
	public List<Orders> getAllOrders() {
		return ors.getAllOrders();
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId, boolean isEnduser) {
		return ors.getAllOrdersByUser(userId, isEnduser);
	}

	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void updateOrder(Orders order) {
		try {
			ors.updateOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refundOrder(Orders order, int vendorId, boolean isEnduser) {
		try {
			ors.refundOrder(order, vendorId, isEnduser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(Orders order) {
		ordersRepository.save(order);
	}
	
	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void deleteOrder(Orders order) {
		try {
			ors.deleteOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// End Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}