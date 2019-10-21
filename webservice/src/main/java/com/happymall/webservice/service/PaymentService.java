package com.happymall.webservice.service;

import java.util.List;

import com.happymall.webservice.domain.Payment;

public interface PaymentService {
	
	List<Payment> getAllPayments();
	// CardDetail id
	List<Payment> getCustomerPayments(int id);
	//Vendor id
	//List<Payment> getVendorPayments(int id);
	
	Payment getPayment(int id);
	Payment getPaymentByOrderId(int id);
	
	void addPayment(Payment payment);
	Payment updatePayment(Payment payment);
	void deletePayment(int id);
	
	Payment approvePayment(Payment payment);
	Payment refundMoney(Payment payment);

}
