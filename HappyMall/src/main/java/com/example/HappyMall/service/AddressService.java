package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.Address;

//ThaoDao created and edited
public interface AddressService {
	List<Address> findByUserId(int userId);

	Address getAddressDefaultByUserId(int userId);

	Address getAddress(int id);

	void save(Address address);

	void delete(Address address);

	Address saveAddress(Address address);
}
