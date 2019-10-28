package com.example.HappyMall.rest.service;

import java.util.List;

import com.example.HappyMall.domain.CardDetail;

public interface CardDetailRestService {
	
	List<CardDetail> findByUserIdAndActiveInd(int userId, char active_ind);
	
	CardDetail getCardDefaultByUserId(int userId);
	
	CardDetail getCardDetail(int id);
	
	void save (CardDetail cardDetail);
	
	void saveAll (List<CardDetail> listCardDetail);
	
	Boolean isCardValid(CardDetail cardDetail);

}
