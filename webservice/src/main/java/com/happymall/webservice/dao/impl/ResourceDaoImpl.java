package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.ResourceDao;
import com.happymall.webservice.domain.Resource;

@Repository
public class ResourceDaoImpl extends GenericDaoImpl<Resource> implements ResourceDao {

	public ResourceDaoImpl() {
		super.setDaoType(Resource.class);
	}
}
