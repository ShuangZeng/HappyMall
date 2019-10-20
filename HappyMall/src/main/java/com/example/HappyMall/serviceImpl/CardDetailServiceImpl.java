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
	public List<CardDetail> findByUserIdAndActiveInd(int userId, char active_ind) {
		// TODO Auto-generated method stub
		return cardDetailRepository.findByUserIdAndActiveInd(userId, active_ind);
	}

	@Override
	public CardDetail getCardDefaultByUserId(int userId) {
		// TODO Auto-generated method stub
		return cardDetailRepository.getCardDefaultByUserId(userId);
	}

	@Override
	public CardDetail getCardDetail(int id) {
		// TODO Auto-generated method stub
		return cardDetailRepository.getOne(id);
	}

	@Override
	public void save(CardDetail cardDetail) {
		// TODO Auto-generated method stub
		cardDetailRepository.save(cardDetail);
	}

}
