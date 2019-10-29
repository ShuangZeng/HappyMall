package com.happymall.webservice.mock;

import com.happymall.webservice.dao.OrderLineDao;
import com.happymall.webservice.dao.impl.OrderLineDaoImpl;
import com.happymall.webservice.domain.OrderLine;

public class OrderLineMock {

	public static OrderLine create() {

		OrderLine O = new OrderLine();
		O.setProduct(ProductMock.create());
		O.setQuantity(4);
		O.setPrice(O.getProduct().getPrice());
		O.setTotal(O.getPrice() * O.getQuantity());

		return O;
	}

	public static OrderLine createAndPersist() {

		OrderLine O = new OrderLine();
		O.setProduct(ProductMock.createAndPersist());
		O.setQuantity(4);
		O.setPrice(O.getProduct().getPrice());
		O.setTotal(O.getPrice() * O.getQuantity());

		OrderLineDao OD = new OrderLineDaoImpl();
		OD.save(O);

		return O;
	}

}
