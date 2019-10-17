package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.CardDetail;
import com.example.HappyMall.repository.CardDetailRepository;
import com.example.HappyMall.service.CardDetailService;

@Service
public class CardDetailServiceImpl implements CardDetailService{

	@Autowired
	private CardDetailRepository cardDetailRepository;
	
	@Override
	public List<CardDetail> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return cardDetailRepository.findByUserId(userId);
	}

}
