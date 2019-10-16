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
import com.example.HappyMall.rest.service.ProductRestService;

import com.example.HappyMall.rest.RestHttpHeader;

@Service
@Transactional
public class ProductRestServiceImpl implements ProductRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	String baseUrl = "http://localhost:8000/products";
	String baseUrlExtended = baseUrl + "/";
	

	@Override
	public List<Product> getAllProducts() {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> productList = Arrays.asList(responseEntity.getBody());
		return null;
	}

	@Override
	public List<Product> getProductsByName(String name) {
		
		return null;
	}

	@Override
	public List<Product> getProductsByVendorName(String name) {
		
		return null;
	}

	@Override
	public void addProduct(Product product) {
		
		
	}

	@Override
	public Product getProduct(int id) {
		
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		
		return null;
	}

	@Override
	public void deleteProduct(int id) {
		
		
	}


}
