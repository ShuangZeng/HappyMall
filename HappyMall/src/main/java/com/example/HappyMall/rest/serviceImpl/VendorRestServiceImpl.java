package com.example.HappyMall.rest.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.rest.RestHttpHeader;
import com.example.HappyMall.rest.service.VendorRestService;



@Service
@Transactional
public class VendorRestServiceImpl implements VendorRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	@Value( "${base.url}" )
	private String baseUrl;
	String serviceUrl = "http://localhost:8000/vendors";
	String serviceUrlExtended = serviceUrl + "/";

	@Override
	public List<User> getAllVendors() {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<User[]> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, User[].class);	
 		List<User> vendorList = Arrays.asList(responseEntity.getBody());
		return vendorList;
	}

	@Override
	public User getVendorByEmail(String email) {
		
		String url = serviceUrlExtended + "email/" + email;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, User.class);	
 		User vendor = responseEntity.getBody();
		return vendor;
	}

	@Override
	public User getVendorByPhone(String phone) {
		
		String url = serviceUrlExtended + "phone/" + phone;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, User.class);	
 		User vendor = responseEntity.getBody();
		return vendor;
	}

	@Override
	public User addVendor(User vendor) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<User> httpEntity = new HttpEntity<User>(vendor, restHelper.getHttpHeaders());
		vendor = restTemplate.postForObject(serviceUrl, httpEntity, User.class);
		return vendor;
	}

	@Override
	public User getVendor(int id) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<User> responseEntity = restTemplate.exchange(serviceUrlExtended + id, HttpMethod.GET, httpEntity, User.class);	
 		User vendor = responseEntity.getBody();
		return vendor;
	}

	@Override
	public User updateVendor(User vendor) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<User> httpEntity = new HttpEntity<User>(vendor, restHelper.getHttpHeaders());
		vendor = restTemplate.patchForObject(serviceUrlExtended + "update", httpEntity, User.class);
		return vendor;
	}

	@Override
	public void deleteVendor(User vendor) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<User> httpEntity = new HttpEntity<User>(vendor, restHelper.getHttpHeaders());
		vendor = restTemplate.patchForObject(serviceUrlExtended + "update", httpEntity, User.class);
	}

}
