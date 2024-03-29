package com.example.HappyMall.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.repository.ProductRepository;
import com.example.HappyMall.rest.service.ProductRestService;
import com.example.HappyMall.service.ProductService;

//Created and Edited by Mohammed Heakal
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

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
		product.setStatus("U");
		product.setCreateDate(new Date());
		productRepository.save(product);
//		return prs.addProduct(product);
		return product;
	}

	@Override
	public Product getProduct(int id) {

		return prs.getProduct(id);
	}

	@Override
	public Product updateProduct(Product product) {
		product.setModifiedDate(new Date());
		productRepository.save(product);
//		return prs.updateProduct(product);
		return product;
	}

	@Override
	public Product deleteProduct(Product product) {
		Optional<Product> productRecordOptional = productRepository.findById(product.getId());
		if (!productRecordOptional.isPresent())
			return null;
		Product productRecord = productRecordOptional.get();
		productRecord.setStatus("D");
		productRepository.save(productRecord);
		return product;
	}

	@Override
	public List<Product> findProductsByVendor(int vendorNumber) {
		return prs.findProductsByVendor(vendorNumber);
	}

	@Override
	public Product approveProduct(Product product) {
		Optional<Product> productRecordOptional = productRepository.findById(product.getId());
		if (!productRecordOptional.isPresent())
			return null;
		Product productRecord = productRecordOptional.get();
		productRecord.setStatus("A");
		productRepository.save(productRecord);
		return product;
	}

	@Override
	public Product blockProduct(Product product) {
		Optional<Product> productRecordOptional = productRepository.findById(product.getId());
		if (!productRecordOptional.isPresent())
			return null;
		Product productRecord = productRecordOptional.get();
		productRecord.setStatus("D");
		productRepository.save(productRecord);
		return product;
	}

	@Override
	public List<Product> getAllProductsMngt() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

}
