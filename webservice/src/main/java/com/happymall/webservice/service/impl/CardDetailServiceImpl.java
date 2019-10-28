package com.happymall.webservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.dao.MockServerDao;
import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.service.CardDetailService;

@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService {
	
	@Autowired
	CardDetailDao cardDetailDao;
	
	@Autowired
	MockServerDao mockServerDao;

	@Override
	public List<CardDetail> findCardByUser(int id) {
		
		return cardDetailDao.findCardByUser(id);
	}

	@Override
	public List<CardDetail> getAllCardDetails() {
		
		return cardDetailDao.findAll();
	}

	@Override
	public CardDetail getCardDetail(int id) {
		
		return cardDetailDao.findOne(id);
	}

	@Override
	public void addCardDetail(CardDetail cardDetail) {
		
		cardDetailDao.save(cardDetail);
		
	}

	@Override
	public CardDetail updateCardDetail(CardDetail cardDetail) {
		
		return cardDetailDao.update(cardDetail);
	}

	@Override
	public void deleteCardDetail(int id) {
		
		cardDetailDao.delete(id);
	}

	@Override
	public Boolean isCardValid(CardDetail cardDetail) {
		
		return mockServerDao.isCardValid(cardDetail);
	}

	@Override
	public CardDetail findDefaultCardByUser(int id) {
		
		return cardDetailDao.findDefaultCardByUser(id);
	}

}
