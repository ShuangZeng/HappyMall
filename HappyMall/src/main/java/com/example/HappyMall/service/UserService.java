package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.User;

public interface UserService {
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User findByEmailAndPassword(String email, String password);

	User saveUser(User user);

	User findUserByFullName(String fullName);

	User findUserByEmail(String email);

	List<User> findAllUsers();

	void deleteUserByUser(User user);

	User blockUser(User user);

	User approveUser(User user);
}
