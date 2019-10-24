package com.example.HappyMall.repository;

public interface ShoppingCartRepository {
	void updateMoneyByOrdersId(int orderId, int tax, int serviceFee);
	void deleteByOrdersIdAndProductId(int ordersId, int productId);
}
