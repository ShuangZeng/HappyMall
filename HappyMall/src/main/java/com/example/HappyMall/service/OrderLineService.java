package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.OrderLine;

public interface OrderLineService {
	List<OrderLine> findByOrdersId (int ordersId);
}