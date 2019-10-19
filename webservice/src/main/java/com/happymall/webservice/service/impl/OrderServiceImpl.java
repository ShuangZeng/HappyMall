package com.happymall.webservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.happymall.webservice.dao.OrderLineDao;
import com.happymall.webservice.dao.OrdersDao;
import com.happymall.webservice.dao.ProductDao;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersDao orderDao;
	@Autowired
	private OrderLineDao olDao;
	@Autowired
	private ProductDao productDao;
	
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

	// End Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
	
	
	
 	//-----------------------------------------------------------------------------------------
 	//Retrieve---------------------------------------------------------------------------------
 	//All retrieving/getting order functions will be declared here
 	//-----------------------------------------------------------------------------------------
 	
 	//START Region: Get specific order---------------------------------------------------------
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
	public Orders getOrderByOrderCode(int userId, String orderCode, boolean forEnduser) {
		Stream<Orders> list = this.getAllOrdersByUser(userId, forEnduser).stream()
									.filter(o -> o.getOrderCode().equalsIgnoreCase(orderCode));
		Orders order = list.findAny().get();
		return order;
	}
	//END Region: Get specific order-----------------------------------------------------------
	
	
	//START Region: Get list of orders---------------------------------------------------------	
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
		List<Orders> list;
		
		if (forEnduser) {
			//Get all orders by enduser id
			list = this.getAllOrders().stream()
						.filter(o -> o.getUser().getId() == userId)
						.collect(Collectors.toList());
		} else {
			//Get all orders by vendor id
			list = olDao.findAll().stream()
					.filter(o -> o.getProduct().getVendor().getId() == userId)
					.map(o -> o.getOrders())
					.collect(Collectors.toList());
		}
		
		return list;
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
		try {
			return orderDao.update(order);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		
		//This code will physically remove the record. Commented out for now
		//orderDao.delete(id);
	}
	
 	//End Delete-------------------------------------------------------------------------------
 	//-----------------------------------------------------------------------------------------
}