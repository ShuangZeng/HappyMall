package com.example.HappyMall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.CardDetail;

//ThaoDao created and edited
@Repository
@Transactional
public interface CardDetailRepository extends JpaRepository<CardDetail, Integer> {

	@Query(value = "select * from card_detail a where a.user_id = :userId and active_ind = :active_ind", nativeQuery = true)
	List<CardDetail> findByUserIdAndActiveInd(int userId, char active_ind);

	@Query(value = "select * from card_detail a where a.user_id = :userId and a.default_card = 1 limit 1", nativeQuery = true)
	CardDetail getCardDefaultByUserId(int userId);
}
