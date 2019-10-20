package com.example.HappyMall.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.repository.OrdersRepository;
import com.example.HappyMall.service.OrdersService;

@Service
public class OrderServiceImpl implements OrdersService {
	
	@PersistenceContext
    private EntityManager em;
	
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

	@Override
	public void updateMoneyByOrdersId(int orderId) {
		// TODO Auto-generated method stub
		ordersRepository.updateMoneyByOrdersId(orderId);
	}

	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		ordersRepository.save(orders);
	}

}
