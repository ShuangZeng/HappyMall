package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.OrdersDao;
import com.happymall.webservice.domain.Orders;

@Repository
public class OrdersDaoImpl extends GenericDaoImpl<Orders> implements OrdersDao {

	public OrdersDaoImpl() {
		super.setDaoType(Orders.class);
	}
}
