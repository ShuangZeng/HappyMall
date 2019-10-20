package com.example.HappyMall.rest.service;

import java.util.List;

import com.example.HappyMall.domain.Product;


public interface ProductRestService {
	
	List<Product> getAllProducts();

	List<Product> getProductsByName(String name);
	
	List<Product> getProductsByVendorName(String name);

 	Product addProduct(Product product);

	Product getProduct(int id);
 
	Product updateProduct(Product product);
	
	void deleteProduct(Product product);
	
	Product approveProduct(Product product);
	Product blockProduct(Product product);
	List<Product> findProductsByVendor(int vendorNumber);

}
