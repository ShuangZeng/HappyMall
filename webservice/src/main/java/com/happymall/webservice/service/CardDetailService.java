package com.happymall.webservice.service;

import java.util.List;

import com.happymall.webservice.domain.CardDetail;

public interface CardDetailService {
	
	List<CardDetail> findCardByUser(int id);
	List<CardDetail> getAllCardDetails();
	CardDetail getCardDetail(int id);
	void addCardDetail(CardDetail cardDetail);
	CardDetail updateCardDetail(CardDetail cardDetail);
	void deleteCardDetail(int id);
	Boolean isCardValid(CardDetail cardDetail);

}
