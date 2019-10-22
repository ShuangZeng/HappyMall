package com.example.HappyMall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.SystemConfig;

@Repository
@Transactional
public interface SystemConfigRepository extends  JpaRepository<SystemConfig, Integer> {

	@Query(value="select * from system_config " + 
			"where applied_date < CURDATE() " + 
			"order by applied_date desc " + 
			"limit 1", nativeQuery = true)
	SystemConfig getToApplied();
}
