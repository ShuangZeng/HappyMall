package com.happymall.webservice.mock;

import java.util.Arrays;

import com.happymall.webservice.dao.OrdersDao;
import com.happymall.webservice.dao.impl.OrdersDaoImpl;
import com.happymall.webservice.domain.OrderLine;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.domain.User;

public class OrderMock {
	
	public static Orders create() {
	
		Orders O = new Orders();
		User U = UserMock.create();
		U.setListOrders(Arrays.asList(O));
		O.setUser(U);
		O.setOrderCode("EEGKN49590");
		
		OrderLine L1 = OrderLineMock.create();
		L1.setOrders(O);
		OrderLine L2 = OrderLineMock.create();
		L2.setOrders(O);
		OrderLine L3 = OrderLineMock.create();
		L3.setOrders(O);
		OrderLine L4 = OrderLineMock.create();
		L4.setOrders(O);
		O.setListOrderLine(Arrays.asList(L1,L2,L3,L4));
		
		return O;
	}
	
	public static Orders createAndPersist() {
		
		Orders O = new Orders();
		User U = UserMock.createAndPersist();
		U.setListOrders(Arrays.asList(O));
		O.setUser(U);
		O.setOrderCode("EEGKN49590");
		
		OrderLine L1 = OrderLineMock.createAndPersist();
		L1.setOrders(O);
		OrderLine L2 = OrderLineMock.createAndPersist();
		L2.setOrders(O);
		OrderLine L3 = OrderLineMock.createAndPersist();
		L3.setOrders(O);
		OrderLine L4 = OrderLineMock.createAndPersist();
		L4.setOrders(O);
		O.setListOrderLine(Arrays.asList(L1,L2,L3,L4));
		
		OrdersDao OD = new OrdersDaoImpl();
		OD.save(O);
		
		return O;
	}

}
