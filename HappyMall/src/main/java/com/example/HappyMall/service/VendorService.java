package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.User;

public interface VendorService {
	
	List<User> getAllVendors();

	User getVendorByEmail(String email);
	
	User getVendorByPhone(String phone);

 	User addVendor(User vendor);

	User getVendor(int id);
 
	User updateVendor(User vendor);
	
	void deleteVendor(User vendor);


}
