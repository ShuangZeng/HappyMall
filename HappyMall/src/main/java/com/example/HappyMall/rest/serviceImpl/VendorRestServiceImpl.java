package com.example.HappyMall.rest.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.rest.RestHttpHeader;
import com.example.HappyMall.rest.service.VendorRestService;



@Service
@Transactional
public class VendorRestServiceImpl implements VendorRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	String baseUrl = "http://localhost:8000/products";
	String baseUrlExtended = baseUrl + "/";	

	@Override
	public List<User> getAllVendors() {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<User[]> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, User[].class);	
 		List<User> vendorList = Arrays.asList(responseEntity.getBody());
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
