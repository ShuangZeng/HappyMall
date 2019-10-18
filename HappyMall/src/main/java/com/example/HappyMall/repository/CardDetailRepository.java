package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HappyMall.domain.*;

@Repository
public interface CardDetailRepository extends  JpaRepository<CardDetail, Integer> {

	@Query(value="select * from card_detail a where a.user_id = :userId", nativeQuery=true)
	List<CardDetail> findByUserId (int userId);
	
	@Query(value="select * from card_detail a where a.user_id = :userId and a.default_card = 1", nativeQuery=true)
	CardDetail getCardDefaultByUserId(int userId);
}
