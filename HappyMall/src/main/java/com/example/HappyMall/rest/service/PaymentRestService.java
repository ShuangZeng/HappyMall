package com.example.HappyMall.rest.service;

import java.util.List;

import com.example.HappyMall.domain.Payment;

public interface PaymentRestService {

	List<Payment> getAllPayments();

	Payment getPayment(int id);

	Payment addPayment(Payment payment);

	Payment updatePayment(Payment payment);

	void deletePayment(Payment payment);

	List<Payment> getCustomerPayments(int id);

	Payment getPaymentByOrderId(int id);

	Payment approvePayment(Payment payment);

	Payment refundMoney(Payment payment);

}
