package com.example.HappyMall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.CardDetail;


public interface CardDetailService {

	List<CardDetail> findByUserId(int userId);
}
