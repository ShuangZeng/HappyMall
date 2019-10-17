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
import com.example.HappyMall.domain.User;
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
	public Optional<Product> findByname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Optional<Product> getProduct(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

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
		return productRepository.findProductsByvendor_id(vendorNumber);
	} 

	@Override
	public Product approveProduct(Product product) {
		Optional<Product> productRecordOptional = productRepository.findById(product.getId());
		if(!productRecordOptional.isPresent()) return null;
		Product productRecord = productRecordOptional.get();
		productRecord.setStatus("A");
		productRepository.save(productRecord);
		return product;
	}

	@Override
	public Product blockProduct(Product product) {
		Optional<Product> productRecordOptional = productRepository.findById(product.getId());
		if(!productRecordOptional.isPresent()) return null;
		Product productRecord = productRecordOptional.get();
		productRecord.setStatus("D");
		productRepository.save(productRecord);
		return product;
	}

	@Override
	public List<Product> findProductsByvendor_id(int vendorId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

