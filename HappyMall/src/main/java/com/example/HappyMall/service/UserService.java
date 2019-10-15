package com.example.HappyMall.service;


import java.util.List;

import javax.validation.Valid;


import com.example.HappyMall.domain.User;
import com.example.HappyMall.domain.UserAuthorization;

public interface UserService {
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findByEmailAndPassword(String email, String password);
	User saveUser(User user);
	User findUserByFullName(String fullName);
	User findUserByEmail(String email);
	List<User> findAllUsers();
}
