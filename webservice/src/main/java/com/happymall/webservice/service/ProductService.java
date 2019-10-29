package com.happymall.webservice.service;

import java.util.List;

import com.happymall.webservice.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getProductsByName(String name);

	List<Product> getProductsByVendorName(String name);

	void addProduct(Product product);

	Product getProduct(int id);

	Product updateProduct(Product product);

	void deleteProduct(int id);

	Product approveProduct(Product product);

	Product blockProduct(Product product);

	List<Product> getProductsByVendorId(int id);

}
