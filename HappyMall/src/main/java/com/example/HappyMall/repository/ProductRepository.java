package com.example.HappyMall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HappyMall.domain.Product;

//Created and Edited by Mohammed Heakal
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findByname(String name);

	List<Product> findProductsByname(String name);

	List<Product> findProductsByvendor_id(int vendorId);

//	@Query("from Product where vendor_id=:vendor_id")
//	List<Product> findByVendorNumber(@Param(value="vendor_id") Integer vendorNumber);
//	
//	@Query("delete from Product where id=:product_id")
//	void deleteProduct(@Param(value="product_id") Integer productId);

}