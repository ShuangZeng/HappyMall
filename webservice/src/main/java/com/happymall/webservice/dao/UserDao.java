package com.happymall.webservice.dao;

import java.util.List;

import com.happymall.webservice.domain.User;

public interface UserDao extends GenericDao<User> {

	List<User> findAllVendors();

	User findByEmail(String email);

	User findByPhone(String phone);

}
