package com.happymall.webservice.mock;

import java.util.Date;

import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.dao.impl.CardDetailDaoImpl;
import com.happymall.webservice.domain.CardDetail;

public class CardDetailMock {

	public static CardDetail create() {
		
		CardDetail C = new CardDetail();
		C.setActive_Ind('A');
		C.setAddress(AddressMock.create());
		C.setCardNumber("4548875999001232");
		C.setExpiredDate(new Date());
		C.setNameOnCard("Buba Bojang");
		C.setRemainingValue(5000.0);
		C.setValue(5000.0);
		C.setType("Visacard");
		
		return C;
	}
	
public static CardDetail createAndPersist() {
		
		CardDetail C = new CardDetail();
		C.setActive_Ind('A');
		C.setAddress(AddressMock.createAndPersist());
		C.setCardNumber("4548875999001232");
		C.setExpiredDate(new Date());
		C.setNameOnCard("Buba Bojang");
		C.setRemainingValue(5000.0);
		C.setValue(5000.0);
		C.setType("Visacard");
		
		//CD.save(C);
		
		return C;
	}
}
