package com.happymall.webservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.UserDao;
import com.happymall.webservice.domain.User;
import com.happymall.webservice.service.VendorService;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllVendors() {
		return userDao.findAllVendors();
	}

	@Override
	public User getVendorByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User getVendorByPhone(String phone) {
		return userDao.findByPhone(phone);
	}

	@Override
	public void addVendor(User vendor) {
		userDao.save(vendor);

	}

	@Override
	public User getVendor(int id) {
		return userDao.findOne(id);
	}

	@Override
	public User updateVendor(User vendor) {
		return userDao.update(vendor);
	}

	@Override
	public void deleteVendor(int id) {
		userDao.delete(id);
	}

}
