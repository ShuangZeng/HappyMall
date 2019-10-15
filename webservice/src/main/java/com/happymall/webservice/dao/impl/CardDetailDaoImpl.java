package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.domain.CardDetail;

@Repository
public class CardDetailDaoImpl extends GenericDaoImpl<CardDetail> implements CardDetailDao {

	public CardDetailDaoImpl() {
		super.setDaoType(CardDetail.class);
	}
}
