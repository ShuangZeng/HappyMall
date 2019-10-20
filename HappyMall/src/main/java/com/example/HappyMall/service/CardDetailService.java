package com.example.HappyMall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.CardDetail;


public interface CardDetailService {

	List<CardDetail> findByUserIdAndActiveInd(int userId, char active_ind);
	
	CardDetail getCardDefaultByUserId(int userId);
	
	CardDetail getCardDetail(int id);
	
	void save (CardDetail cardDetail);
}
