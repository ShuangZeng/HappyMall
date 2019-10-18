package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Orders;

@Repository
@Transactional
public interface OrdersRepository  extends  JpaRepository<Orders, Integer>, ShoppingCartRepository {
	Orders findByStatus(String status);
	
	@Query(value="select * from happymall.orders where user_id = :userId and status = :status", nativeQuery=true)
	List<Orders> findByStatusAndUserId(String status, int userId);
	
}
