package com.example.HappyMall.repository;

public interface ShoppingCartRepository {
	void updateMoneyByOrdersId(int orderId);
	void deleteByOrdersIdAndProductId(int ordersId, int productId);
}
