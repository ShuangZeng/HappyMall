package com.happymall.webservice.dao;

import java.util.List;

import com.happymall.webservice.domain.Product;

public interface ProductDao extends GenericDao<Product> {

	List<Product> findByName(String name);

	public List<Product> findByVendorName(String name);

	Product approveProduct(Product product);

	Product blockProduct(Product product);

	List<Product> getProductsByVendorId(int id);

}
