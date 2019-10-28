package com.example.HappyMall.rest.service;

import com.example.HappyMall.domain.MockServer;

//ThaoDao created and edited
public interface MockServerService {
	MockServer findByNameOnCardAndCardNumberAndCvv(String nameOnCard, String cardNumber, String cvv);
}
