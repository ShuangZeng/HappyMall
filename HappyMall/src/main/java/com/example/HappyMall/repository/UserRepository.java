package com.example.HappyMall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HappyMall.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByFullName(String fullName);

	User findByFullNameAndPassword(String fullName, String password);

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);
}