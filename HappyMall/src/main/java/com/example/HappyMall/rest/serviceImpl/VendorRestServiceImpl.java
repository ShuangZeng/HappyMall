package com.example.HappyMall.rest.serviceImpl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.rest.service.VendorRestService;



@Service
@Transactional
public class VendorRestServiceImpl implements VendorRestService {
	
	

	@Override
	public List<User> getAllVendors() {
		return null;
	}

	@Override
	public User getVendorByEmail(String email) {
		return null;
	}

	@Override
	public User getVendorByPhone(String phone) {
		return null;
	}

	@Override
	public void addVendor(User vendor) {
		
		
	}

	@Override
	public User getVendor(int id) {
		return null;
	}

	@Override
	public User updateVendor(User vendor) {
		return null;
	}

	@Override
	public void deleteVendor(int id) {
		
	}

}
