package com.happymall.webservice.mock;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happymall.webservice.dao.AddressDao;
import com.happymall.webservice.dao.CardDetailDao;
import com.happymall.webservice.dao.UserDao;
import com.happymall.webservice.dao.impl.UserDaoImpl;
import com.happymall.webservice.domain.Address;
import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.domain.User;

@Component
public class UserMock {
	
	public static User create() {
		
		User U = new User();
		U.setFullName("Buba Bojang");
		U.setEmail("buba.bbojang@gmail.com");
		U.setActive_Ind('A');
		U.setListAddress(Arrays.asList(AddressMock.create()));
		CardDetail C = CardDetailMock.create();
		C.setUser(U);
		U.setListCardDetail(Arrays.asList(C));
		
		return U;
	}
	
	public static User createAndPersist() {
		
		User U = new User();
		U.setFullName("Buba Bojang");
		U.setEmail("buba.bbojang@gmail.com");
		U.setActive_Ind('A');
		Address A = AddressMock.createAndPersist();
		A.setUser(U);
		
		U.setListAddress(Arrays.asList(A));
		CardDetail C = CardDetailMock.createAndPersist();
		C.setUser(U);
		U.setListCardDetail(Arrays.asList(C));		
		
//		AD.save(A);
//		CD.save(C);
//		UD.save(U);
		
		return U;
	}

}
