package com.example.HappyMall.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.HappyMall.domain.Product;

@Repository
public interface ProductPageAndSortingRepository extends PagingAndSortingRepository<Product, Long> {

}
