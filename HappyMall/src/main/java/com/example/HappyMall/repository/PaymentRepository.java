package com.example.HappyMall.repository;

import org.springframework.stereotype.Repository;
import com.example.HappyMall.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("paymentReposiroty")
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
