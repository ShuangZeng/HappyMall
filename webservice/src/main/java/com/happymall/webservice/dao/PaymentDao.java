package com.happymall.webservice.dao;

import java.util.List;

import com.happymall.webservice.domain.Payment;

public interface PaymentDao extends GenericDao<Payment> {
	
	Payment findPaymentByOrderId(int id);
	
	Payment approvePayment(Payment payment);
	
	Payment refundMoney(Payment payment);
	
	// CardDetail id
	List<Payment> findCustomerPayments(int id);
	
	// Vendor id
	// List<Payment> findVendorPayments(int id);

}
