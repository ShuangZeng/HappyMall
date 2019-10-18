package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.HappyMall.domain.Orders;

public interface OrdersRepository  extends  JpaRepository<Orders, Integer>, OrdersCustomRepository {
	Orders findByStatus(String status);
	
	@Query(value="select * from happymall.orders where user_id = :userId and status = :status", nativeQuery=true)
	List<Orders> findByStatusAndUserId(String status, int userId);
	
}
