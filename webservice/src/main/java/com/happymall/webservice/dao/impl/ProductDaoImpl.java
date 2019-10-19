package com.happymall.webservice.dao.impl;

import java.util.List;

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
	

}
