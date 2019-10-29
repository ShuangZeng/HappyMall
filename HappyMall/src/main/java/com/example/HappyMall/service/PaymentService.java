package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.Payment;

public interface PaymentService {
	List<Payment> findAllPayment();

	void save(Payment payment);
}
