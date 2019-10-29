package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.UserAuthorizationDao;
import com.happymall.webservice.domain.UserAuthorization;

@Repository
public class UserAuthorizationDaoImpl extends GenericDaoImpl<UserAuthorization> implements UserAuthorizationDao {

	public UserAuthorizationDaoImpl() {
		super.setDaoType(UserAuthorization.class);
	}

}
