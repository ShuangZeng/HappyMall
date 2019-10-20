package com.happymall.webservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.dao.ProductDao;
import com.happymall.webservice.domain.Product;
import com.happymall.webservice.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		
		return productDao.findAll();
	}

	@Override
	public List<Product> getProductsByName(String name) {
		
		return productDao.findByName(name);
	}

	@Override
	public List<Product> getProductsByVendorName(String name) {
		
		return productDao.findByVendorName(name);
	}

	@Override
	public void addProduct(Product product) {
		
		productDao.save(product);
	}

	@Override
	public Product getProduct(int id) {
		
		return productDao.findOne(id);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productDao.update(product);
	}

	@Override
	public void deleteProduct(int id) {
		
		productDao.delete(id);
	}

	@Override
	public Product approveProduct(Product product) {
		
		return productDao.approveProduct(product);
	}

	@Override
	public Product blockProduct(Product product) {

		return productDao.blockProduct(product);
	}

	@Override
	public List<Product> getProductsByVendorId(int id) {
		// TODO Auto-generated method stub
		return productDao.getProductsByVendorId(id);
	}


}
