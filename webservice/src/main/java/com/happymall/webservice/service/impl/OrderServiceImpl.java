package com.happymall.webservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.OrderLineDao;
import com.happymall.webservice.dao.OrdersDao;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersDao orderDao;
	@Autowired
	private OrderLineDao olDao;

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void addOrder(Orders order) {
		try {
			orderDao.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// End
	// Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public Orders getOrder(int id) {
		try {
			return orderDao.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Orders> getAllOrders() {
		try {
			return orderDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId, boolean isEnduser) {
		List<Orders> list;

		if (isEnduser) {
			// Get all orders by enduser id
			list = this.getAllOrders().stream().filter(o -> o.getUser().getId() == userId)
					.filter(o -> !o.getStatus().equalsIgnoreCase("ShoppingCart"))
					.filter(o -> !o.getStatus().equalsIgnoreCase("New")).collect(Collectors.toList());
		} else {
			// Get all orders by vendor id
			list = olDao.findAll().stream().filter(o -> o.getProduct().getVendor().getId() == userId)
					.map(o -> o.getOrders()).collect(Collectors.toList());
		}

		return list;
	}

	// End
	// Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void updateOrder(Orders order) {
		try {
			orderDao.update(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refundOrder(Orders order, int userId, boolean isEnduser) {
		try {
			orderDao.update(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// End
	// Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void deleteOrder(int id) {
		// This code will physically remove the record. Commented out for now
		// orderDao.delete(id);
	}

	// End
	// Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}