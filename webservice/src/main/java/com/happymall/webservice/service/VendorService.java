package com.happymall.webservice.service;

import java.util.List;

import com.happymall.webservice.domain.User;

public interface VendorService {

	List<User> getAllVendors();

	User getVendorByEmail(String email);

	User getVendorByPhone(String phone);

	void addVendor(User vendor);

	User getVendor(int id);

	User updateVendor(User vendor);

	void deleteVendor(int id);

}
