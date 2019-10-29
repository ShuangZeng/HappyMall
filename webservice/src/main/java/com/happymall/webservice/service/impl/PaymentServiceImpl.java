package com.happymall.webservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.PaymentDao;
import com.happymall.webservice.domain.Payment;
import com.happymall.webservice.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Override
	public List<Payment> getAllPayments() {

		return paymentDao.findAll();
	}

	// CardDetail id
	@Override
	public List<Payment> getCustomerPayments(int id) {

		return paymentDao.findCustomerPayments(id);
	}

	// Vendor id
//	@Override
//	public List<Payment> getVendorPayments(int id) {
//		
//		return paymentDao.findVendorPayments(id);
//	}

	@Override
	public Payment getPayment(int id) {

		return paymentDao.findOne(id);
	}

	@Override
	public Payment getPaymentByOrderId(int id) {

		return paymentDao.findPaymentByOrderId(id);
	}

	@Override
	public void addPayment(Payment payment) {

		try {
			paymentDao.savePayment(payment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Payment updatePayment(Payment payment) {

		return paymentDao.update(payment);
	}

	@Override
	public void deletePayment(int id) {

		paymentDao.delete(id);
	}

	@Override
	public Payment approvePayment(Payment payment) {

		return paymentDao.approvePayment(payment);
	}

	@Override
	public Payment refundMoney(Payment payment) {

		return paymentDao.refundMoney(payment);
	}

}
