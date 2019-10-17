package com.example.HappyMall.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Orders;

public interface OrdersService {

	Orders findByStatus(String status);
	
	List<Orders> findByStatusAndUserId(String status, int userId);
}
