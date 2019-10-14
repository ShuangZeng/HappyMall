package com.happymall.webservice.mock;


import com.happymall.webservice.domain.Address;

public class AddressMock {
	
	public static Address create() {
		Address A = new Address();
		A.setLineOne("1000 N 4th St.");
		A.setLineTwo("MR # 100");
		A.setCity("Fairfield");
		A.setState("IA");
		A.setZipcode("52557");
		A.setUser(null);
		return A;
	}
	
	public static Address createAndPersist() {
		Address A = new Address();
		A.setLineOne("1000 N 4th St.");
		A.setLineTwo("MR # 100");
		A.setCity("Fairfield");
		A.setState("IA");
		A.setZipcode("52557");
		//A.setUser(null);
		
		//AD.save(A);
		
		return A;
	}

}
