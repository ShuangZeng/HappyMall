package com.happymall.webservice.service;

import javax.mail.MessagingException;
import com.happymall.webservice.domain.Orders;


public interface EmailService {
	
	public void notifyBuyerOfPurchase(Orders order) throws MessagingException;

}
