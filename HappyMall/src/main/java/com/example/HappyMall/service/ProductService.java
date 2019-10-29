package com.example.HappyMall.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.HappyMall.domain.Product;

//Created and Edited by Mohammed Heakal
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
	Product deleteProduct(Product product);
	
	List<Product> getAllProductsMngt();
}