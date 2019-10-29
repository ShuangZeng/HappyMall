package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.RoleDao;
import com.happymall.webservice.domain.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super.setDaoType(Role.class);
	}

}
