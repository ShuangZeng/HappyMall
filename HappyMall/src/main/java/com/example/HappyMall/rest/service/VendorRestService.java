package com.example.HappyMall.rest.service;

import java.util.List;

import com.example.HappyMall.domain.User;

public interface VendorRestService {
	
	List<User> getAllVendors();

	User getVendorByEmail(String email);
	
	User getVendorByPhone(String phone);

 	void addVendor(User vendor);

	User getVendor(int id);
 
	User updateVendor(User vendor);
	
	void deleteVendor(int id);

}
