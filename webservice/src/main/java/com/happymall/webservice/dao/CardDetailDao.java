package com.happymall.webservice.dao;

import java.util.List;

import com.happymall.webservice.domain.CardDetail;

public interface CardDetailDao extends GenericDao<CardDetail> {
	
	List<CardDetail> findCardByUser(int id);
	

}
