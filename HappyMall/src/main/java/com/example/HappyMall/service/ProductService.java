package com.example.HappyMall.service;

import java.util.List;
import com.example.HappyMall.domain.Product;

public interface ProductService{
	
	void addProduct(Product product);
	Product approveProduct(Product product);
	Product blockProduct(Product product);
	List<Product> findAllProducts();
	List<Product> findProductsByProductName(String name);
	List<Product> findProductsByVendor(int vendorNumber);
	Product getProduct(Integer id);
	List<Product> findProductsByvendor_id(int vendorId);

}