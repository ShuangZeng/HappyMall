package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.Product;

public interface ProductService{
	
	Product addProduct(Product product);
	Product approveProduct(Product product);
	Product blockProduct(Product product);
	List<Product> findProductsByVendor(int vendorNumber);
	List<Product> getAllProducts();
	List<Product> getProductsByName(String name);
	List<Product> getProductsByVendorName(String name);
	Product getProduct(int id);
	Product updateProduct(Product product);
	void deleteProduct(Product product);
}