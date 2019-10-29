package com.example.HappyMall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.domain.MockServer;

//ThaoDao created and edited
@Repository
@Transactional
public interface MockServerRepository extends JpaRepository<MockServer, Integer> {

	MockServer findByNameOnCardAndCardNumberAndCvv(String nameOnCard, String cardNumber, String cvv);
}
