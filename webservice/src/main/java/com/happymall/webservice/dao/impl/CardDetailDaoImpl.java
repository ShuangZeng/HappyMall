package com.happymall.webservice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.dao.MockServerDao;
import com.happymall.webservice.domain.CardDetail;

@Repository
public class CardDetailDaoImpl extends GenericDaoImpl<CardDetail> implements CardDetailDao {

	public CardDetailDaoImpl() {
		super.setDaoType(CardDetail.class);
	}

	@Autowired
	MockServerDao mockServerDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<CardDetail> findCardByUser(int id) {

		Query query = entityManager.createQuery("select c from CardDetail c join c.user u  where u.id  =:id");
		return (List<CardDetail>) query.setParameter("id", id).getResultList();
	}

	@Override
	public CardDetail findDefaultCardByUser(int id) {

		Query query = entityManager
				.createQuery("select c from CardDetail c join c.user u  where u.id  =:id and c.default_card =:flag");
		return (CardDetail) query.setParameter("id", id).setParameter("flag", true).getSingleResult();
	}

}
