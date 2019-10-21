package com.example.HappyMall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.*;


public interface AddressService {
	List<Address> findByUserId(int userId);
	Address getAddressDefaultByUserId(int userId);
	Address getAddress(int id);
	Address saveAddress(Address address);
}
