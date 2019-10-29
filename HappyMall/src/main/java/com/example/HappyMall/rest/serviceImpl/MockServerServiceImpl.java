package com.example.HappyMall.rest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.MockServer;
import com.example.HappyMall.repository.MockServerRepository;
import com.example.HappyMall.rest.service.MockServerService;

//ThaoDao created and edited
@Service
@Transactional
public class MockServerServiceImpl implements MockServerService {

	@Autowired
	private MockServerRepository mockServerRepository;
	
	@Override
	public MockServer findByNameOnCardAndCardNumberAndCvv(String nameOnCard, String cardNumber, String cvv) {
		// TODO Auto-generated method stub
		return mockServerRepository.findByNameOnCardAndCardNumberAndCvv(nameOnCard, cardNumber, cvv);
	}

}
