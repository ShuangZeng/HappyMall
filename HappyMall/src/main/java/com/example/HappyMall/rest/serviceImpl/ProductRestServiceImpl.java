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

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.rest.service.ProductRestService;

import com.example.HappyMall.rest.RestHttpHeader;

@Service
@Transactional
public class ProductRestServiceImpl implements ProductRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	@Value( "${base.url}" )
	private String baseUrl;
	String serviceUrl = "http://localhost:8000/products";
	String serviceUrlExtended = serviceUrl + "/";
	
	

	@Override
	public List<Product> getAllProducts() {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> productList = Arrays.asList(responseEntity.getBody());
		return productList;
	}

	@Override
	public List<Product> getProductsByName(String name) {
		
		String url = serviceUrlExtended + "name/" + name;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url , HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> productList = Arrays.asList(responseEntity.getBody());
		return productList;
	}

	@Override
	public List<Product> getProductsByVendorName(String name) {
		
		String url = serviceUrlExtended + "vendor/" + name;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url , HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> productList = Arrays.asList(responseEntity.getBody());
		return productList;
	}

	@Override
	public Product addProduct(Product product) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		//product = restTemplate.postForObject(serviceUrl, httpEntity, Product.class);
		restTemplate.exchange(serviceUrl, HttpMethod.POST, httpEntity, Product.class);	
		return product;
	}

	@Override
	public Product getProduct(int id) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product> responseEntity = restTemplate.exchange(serviceUrlExtended + id, HttpMethod.GET, httpEntity, Product.class);	
 		Product product = responseEntity.getBody();
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		product = restTemplate.patchForObject(serviceUrlExtended + "update", httpEntity, Product.class);
		return product;
	}

	@Override
	public void deleteProduct(Product product) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		product = restTemplate.patchForObject(serviceUrlExtended + "delete", httpEntity, Product.class);
	}

	@Override
	public Product approveProduct(Product product) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		product = restTemplate.patchForObject(serviceUrlExtended + "approve", httpEntity, Product.class);
		return product;
	}

	@Override
	public Product blockProduct(Product product) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, restHelper.getHttpHeaders());
		product = restTemplate.patchForObject(serviceUrlExtended + "block", httpEntity, Product.class);
		return product;
	}

	@Override
	public List<Product> findProductsByVendor(int vendorNumber) {
		String url = serviceUrlExtended + "by/vendor/" + vendorNumber;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url , HttpMethod.GET, httpEntity, Product[].class);	
 		List<Product> productList = Arrays.asList(responseEntity.getBody());
		return productList;
	}

}
