package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.Address;

//ThaoDao created and edited
@Repository
@Transactional
public interface AddressRepository extends  JpaRepository<Address, Integer>  {

	@Query(value="select * from address a where a.user_id = :userId", nativeQuery=true)
	List<Address> findByUserId(int userId);
	
	@Query(value="select * from address a where a.user_id = :userId and a.default_addr = 1", nativeQuery=true)
	Address getAddressDefaultByUserId(int userId);
}
