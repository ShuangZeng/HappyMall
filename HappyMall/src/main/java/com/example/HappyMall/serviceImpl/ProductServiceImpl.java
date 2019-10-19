package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.rest.service.ProductRestService;
import com.example.HappyMall.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRestService prs;

	@Override
	public List<Product> getAllProducts() {
		
		return prs.getAllProducts();
	}

	@Override
	public List<Product> getProductsByName(String name) {
		
		return prs.getProductsByName(name);
	}

	@Override
	public List<Product> getProductsByVendorName(String name) {
		
		return prs.getProductsByVendorName(name);
	}

	@Override
	public Product addProduct(Product product) {
		
		return prs.addProduct(product);
	}

	@Override
	public Product getProduct(int id) {
		
		return prs.getProduct(id);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return prs.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		
		prs.deleteProduct(product);
	}

}
