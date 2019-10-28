package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.OrderLine;

//ThaoDao created and edited
@Repository
@Transactional
public interface OrderLineRepository extends  JpaRepository<OrderLine, Integer>, ShoppingCartRepository  {

	@Query(value="select * from happymall.order_line where orders_id = :ordersId", nativeQuery=true)
	List<OrderLine> findByOrdersId (int ordersId);
	
	@Query(value="select * from order_line where orders_id = :ordersId and product_id = :productId", nativeQuery=true)
	OrderLine getByOrderIdAndProductId(int ordersId, int productId);
	
}
