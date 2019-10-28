package com.example.HappyMall.rest.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.HappyMall.domain.CardDetail;
import com.example.HappyMall.domain.Product;
import com.example.HappyMall.rest.RestHttpHeader;
import com.example.HappyMall.rest.service.CardDetailRestService;

@Service
@Transactional
public class CardDetailRestServiceImpl implements CardDetailRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	@Value( "${base.url}" )
	private String baseUrl;
	String serviceUrl = "http://localhost:8000/cards";
	String serviceUrlExtended = serviceUrl + "/";

	@Override
	public List<CardDetail> findByUserIdAndActiveInd(int userId, char active_ind) {
		
		String url = serviceUrl + "/all/" + userId;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<CardDetail[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CardDetail[].class);	
 		List<CardDetail> cardList = Arrays.asList(responseEntity.getBody());
		return cardList;
	}

	@Override
	public CardDetail getCardDefaultByUserId(int userId) {
		
		String url = serviceUrlExtended + "default/" + userId;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<CardDetail> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CardDetail.class);	
		CardDetail card = responseEntity.getBody();
		return card;
	}

	@Override
	public CardDetail getCardDetail(int id) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<CardDetail> responseEntity = restTemplate.exchange(serviceUrlExtended + id, HttpMethod.GET, httpEntity, CardDetail.class);	
		CardDetail card = responseEntity.getBody();
		return card;
	}

	@Override
	public void save(CardDetail cardDetail) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<CardDetail> httpEntity = new HttpEntity<CardDetail>(cardDetail, restHelper.getHttpHeaders());
		restTemplate.exchange(serviceUrl, HttpMethod.POST, httpEntity, CardDetail.class);	
	}

	@Override
	public void saveAll(List<CardDetail> listCardDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean isCardValid(CardDetail cardDetail) {
		
		String url = serviceUrlExtended + "is-valid";
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<CardDetail> httpEntity = new HttpEntity<CardDetail>(cardDetail, restHelper.getHttpHeaders());
		ResponseEntity<CardDetail> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CardDetail.class);	
		CardDetail card = responseEntity.getBody();
		if(card == null)
			return false;
		
		return true;
	}
	
	

}
