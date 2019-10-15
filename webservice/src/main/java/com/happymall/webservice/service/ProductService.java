package com.happymall.webservice.service;

import java.util.List;
import java.util.UUID;

import com.happymall.webservice.domain.Product;


public interface ProductService {
	
	List<Product> getAllProducts();

	List<Product> getProductsByName(String name);
	
	List<Product> getProductsByVendorName(String name);

 	void addProduct(Product product);

	Product getProduct(UUID id);
 
	Product updateProduct(Product product);
	
	void deleteProduct(UUID id);

}
