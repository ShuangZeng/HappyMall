package com.happymall.webservice.dao.impl;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.AddressDao;
import com.happymall.webservice.domain.Address;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {
	
	public AddressDaoImpl() {
		super.setDaoType(Address.class);
	}

}
