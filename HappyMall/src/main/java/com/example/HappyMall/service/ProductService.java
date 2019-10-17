package com.example.HappyMall.service;

import java.util.List;
import java.util.Optional;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.repository.ProductRepository;


public interface ProductService{
	
	void addProduct(Product product);
	Product approveProduct(Product product);
	Product blockProduct(Product product);
//	Optional<Product> getProduct(Integer id);

//	void deleteProduct(Integer id);

	List<Product> findAllProducts();

//	void setStock(String productNumber, int quantity, String locationCode);
//
//	Stock getStock(String productNumber);

	List<Product> findProductsByProductName(String name);

//	Product getProductByProductNumber(String productNumber);

	List<Product> findProductsByVendor(int vendorNumber);
<<<<<<< HEAD
	
=======

	Product getProduct(Integer id);
>>>>>>> 2d0348c178a55007bb829a9f5a461e2fe787e111
}