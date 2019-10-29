package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.OrderLineDao;
import com.happymall.webservice.domain.OrderLine;

@Repository
public class OrderLineDaoImpl extends GenericDaoImpl<OrderLine> implements OrderLineDao {

	public OrderLineDaoImpl() {
		super.setDaoType(OrderLine.class);
	}

}
