package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HappyMall.domain.Address;

@Repository
public interface AddressRepository extends  JpaRepository<Address, Integer>  {

	@Query(value="select * from Address a where a.user_id = :userId", nativeQuery=true)
	List<Address> findByUserId(int userId);
}
