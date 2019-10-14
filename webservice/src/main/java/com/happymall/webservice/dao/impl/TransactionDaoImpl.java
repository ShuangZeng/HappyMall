package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.TransactionDao;
import com.happymall.webservice.domain.Transaction;

@Repository
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao {
	
	public TransactionDaoImpl() {
		super.setDaoType(Transaction.class);
	}

}
