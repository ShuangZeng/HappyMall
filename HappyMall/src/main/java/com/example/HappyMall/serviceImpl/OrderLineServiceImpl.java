package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.OrderLine;
import com.example.HappyMall.repository.OrderLineRepository;
import com.example.HappyMall.service.OrderLineService;

//ThaoDao created and edited
@Service
public class OrderLineServiceImpl implements OrderLineService {

	@Autowired
	private OrderLineRepository orderLineRepository;
	
	@Override
	public List<OrderLine> findByOrdersId(int ordersId) {
		// TODO Auto-generated method stub
		return orderLineRepository.findByOrdersId(ordersId);
	}

	@Override
	public OrderLine save(OrderLine orderline) {
		// TODO Auto-generated method stub
		return orderLineRepository.save(orderline);
	}

	@Override
	public OrderLine getByOrderIdAndProductId(int ordersId, int productId) {
		// TODO Auto-generated method stub
		return orderLineRepository.getByOrderIdAndProductId(ordersId, productId);
	}

	@Override
	public void deleteByOrdersIdAndProductId(int ordersId, int productId) {
		// TODO Auto-generated method stub
		orderLineRepository.deleteByOrdersIdAndProductId(ordersId, productId);
	}

	@Override
	public OrderLine getOrderLine(int id) {
		// TODO Auto-generated method stub
		return orderLineRepository.getOne(id);
	}

	@Override
	public void saveAll(List<OrderLine> listOrderLine) {
		// TODO Auto-generated method stub
		orderLineRepository.saveAll(listOrderLine);
	}

}
