package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.OrderLine;

//ThaoDao created and edited
public interface OrderLineService {
	List<OrderLine> findByOrdersId(int ordersId);

	OrderLine save(OrderLine orderline);

	OrderLine getByOrderIdAndProductId(int ordersId, int productId);

	void deleteByOrdersIdAndProductId(int ordersId, int productId);

	OrderLine getOrderLine(int id);

	void saveAll(List<OrderLine> listOrderLine);
}
