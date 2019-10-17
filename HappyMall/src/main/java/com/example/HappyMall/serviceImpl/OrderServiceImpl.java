package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.repository.OrdersRepository;
import com.example.HappyMall.service.OrdersService;

@Service
public class OrderServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public Orders findByStatus(String status) {
		// TODO Auto-generated method stub
		return ordersRepository.findByStatus(status);
	}

	@Override
	public List<Orders> findByStatusAndUserId(String status, int userId) {
		// TODO Auto-generated method stub
		return ordersRepository.findByStatusAndUserId(status, userId);
	}

}
