package com.example.HappyMall.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.HappyMall.domain.Product;

import com.example.HappyMall.repository.ProductRepository;
import com.example.HappyMall.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getProduct(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	

//	@Override
//	public void deleteProduct(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public List<Product> findProductsByProductName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByVendor(int vendorNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

