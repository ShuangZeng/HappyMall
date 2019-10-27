package com.happymall.webservice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.dao.OrdersDao;
import com.happymall.webservice.dao.PaymentDao;
import com.happymall.webservice.dao.TransactionDao;
import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.domain.Payment;
import com.happymall.webservice.domain.Transaction;

@Repository
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao {
	
	@Autowired
	TransactionDao transDao;
	
	@Autowired
	CardDetailDao cardDao;

	public PaymentDaoImpl() {
		super.setDaoType(Payment.class);
	}
	
	public void savePayment(Payment payment) throws Exception {
		
		CardDetail card = cardDao.findOne(payment.getCardDetail().getId());
		if(card.getRemainingValue() < payment.getPaymentTotal()) {
			throw new Exception("You have insufficient amount in your account!");
		}
		super.save(payment);
		card.setRemainingValue(card.getRemainingValue() - payment.getPaymentTotal());
		
	}

	@Override
	public Payment findPaymentByOrderId(int id) {
		
		Query query = entityManager.createQuery("select p from Payment p join p.orders o  where o.id  =:id");
		Payment payment = (Payment) query.setParameter("id", id).getSingleResult();
		return payment;
	}

	@Override
	public Payment approvePayment(Payment payment) {
		
		payment.setStatus("A");
		for(Transaction T : payment.getListTransaction()) {
			T.setIsApproved("A");
			transDao.update(T);
		}
		update(payment);
		return payment;
	}

	@Override
	public Payment refundMoney(Payment payment) {
		
		for(Transaction T : payment.getListTransaction()) {
			Transaction N = new Transaction();
			N.setCreateDate(T.getCreateDate());
			N.setIsApproved(T.getIsApproved());
			N.setModifiedDate(T.getModifiedDate());
			N.setPayment(T.getPayment());
			N.setPaymentTotal(T.getPaymentTotal() * -1);
			transDao.save(N);
			payment.getListTransaction().add(N);
		}
		update(payment);
		return payment;
	}

	// CardDetail id
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findCustomerPayments(int id) {
		
		Query query = entityManager.createQuery("select p from Payment p join p.cardDetail c where c.id  =:id");
		return (List<Payment>) query.setParameter("id", id).getResultList();
	}

	// Vendor id
//	@Override
//	public List<Payment> findVendorPayments(int id) {
//		
//		Query query = entityManager.createQuery("select p from Payment p join p.orders o join c.user v where c.id  =:id");
//		return (List<Payment>) query.setParameter("id", id).getResultList();
//	}
}
