package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.rest.service.VendorRestService;
import com.example.HappyMall.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRestService vrs;

	@Override
	public List<User> getAllVendors() {

		return vrs.getAllVendors();
	}

	@Override
	public User getVendorByEmail(String email) {

		return vrs.getVendorByEmail(email);
	}

	@Override
	public User getVendorByPhone(String phone) {

		return vrs.getVendorByPhone(phone);
	}

	@Override
	public User addVendor(User vendor) {

		return vrs.addVendor(vendor);
	}

	@Override
	public User getVendor(int id) {

		return vrs.getVendor(id);
	}

	@Override
	public User updateVendor(User vendor) {

		return vrs.updateVendor(vendor);
	}

	@Override
	public void deleteVendor(User vendor) {

		vrs.deleteVendor(vendor);

	}

}
