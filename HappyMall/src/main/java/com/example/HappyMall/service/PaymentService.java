package com.example.HappyMall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Payment;

public interface PaymentService {
	List<Payment> findAllPayment();
}
