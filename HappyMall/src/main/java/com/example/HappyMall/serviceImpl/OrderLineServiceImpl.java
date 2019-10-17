package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.repository.OrderLineRepository;
import com.example.HappyMall.service.OrderLineService;

@Service
public class OrderLineServiceImpl implements OrderLineService {

	@Autowired
	private OrderLineRepository orderLineRepository;
	
	@Override
	public List<OrderLine> findByOrdersId(int ordersId) {
		// TODO Auto-generated method stub
		return orderLineRepository.findByOrdersId(ordersId);
	}

}
