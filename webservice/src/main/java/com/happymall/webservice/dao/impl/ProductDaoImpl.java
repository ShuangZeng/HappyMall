package com.happymall.webservice.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.ProductDao;
import com.happymall.webservice.domain.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {
	
	public ProductDaoImpl() {
		super.setDaoType(Product.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByName(String name){
		
		Query query = entityManager.createQuery("select p from Product p  where p.name like :name");
		return (List<Product>) query.setParameter("name", "%"+name+"%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByVendorName(String name){
		
		Query query = entityManager.createQuery("select p from Product p join p.vendor v  where v.fullName like :name");
		return (List<Product>) query.setParameter("name", "%"+name+"%").getResultList();
	}

	@Override
	public Product approveProduct(Product product) {
		Product productRecord = findOne(product.getId());
		productRecord.setStatus("A");
		save(productRecord);
		return product;
	}

	@Override
	public Product blockProduct(Product product) {
		Product productRecord = findOne(product.getId());
		productRecord.setStatus("D");
		save(productRecord);
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByVendorId(int id) {
		
		Query query = entityManager.createQuery("select p from Product p join p.vendor v  where v.id = :id");
		return (List<Product>) query.setParameter("id", id).getResultList();
	}
	

}
