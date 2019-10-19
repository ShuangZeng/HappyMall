package com.happymall.webservice.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.ProductDao;
import com.happymall.webservice.mock.OrderMock;
//import com.happymall.webservice.mock.ProductMock;
import com.happymall.webservice.service.EmailService;

@Component
@Transactional
public class MessageSender {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ProductDao productDao;
	
	@EventListener
	public void run(ContextRefreshedEvent event) {

		
//		try {
//			this.emailService.notifyBuyerOfPurchase(OrderMock.create());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		//productDao.save(ProductMock.createAndPersist());
		
	}
	
	
	
	
}
