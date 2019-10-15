package com.example.HappyMall.service;

import java.util.List;
import java.util.Optional;

import com.example.HappyMall.domain.Product;


public interface ProductService {
	
	void addProduct(Product product);

	Optional<Product> getProduct(Integer id);

	void deleteProduct(Integer id);

	List<Product> getAllProducts();

//	void setStock(String productNumber, int quantity, String locationCode);
//
//	Stock getStock(String productNumber);

	List<Product> findProductsByProductName(String name);

	Product getProductByProductNumber(String productNumber);

	List<Product> findProductsByVendor(int vendorNumber);
}