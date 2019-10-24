package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Payment;
import com.example.HappyMall.repository.PaymentRepository;
import com.example.HappyMall.service.PaymentService;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public List<Payment> findAllPayment() {
		
		return paymentRepository.findAll();
	}

	@Override
	public void save(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepository.save(payment);
	}
	
	
}
