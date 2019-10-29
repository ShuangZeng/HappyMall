package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Payment;
import com.example.HappyMall.repository.PaymentRepository;
import com.example.HappyMall.rest.service.PaymentRestService;
import com.example.HappyMall.service.PaymentService;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	PaymentRestService prs;

	@Override
	public List<Payment> findAllPayment() {
		
		//return prs.getAllPayments();
		return paymentRepository.findAll();
	}

	@Override
	public void save(Payment payment) {
		// TODO Auto-generated method stub
		//prs.addPayment(payment);
		paymentRepository.save(payment);
	}
	
	
}
