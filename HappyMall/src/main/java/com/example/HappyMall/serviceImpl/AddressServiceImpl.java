package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.repository.AddressRepository;
import com.example.HappyMall.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return addressRepository.findByUserId(userId);
	}

	@Override
	public Address getAddressDefaultByUserId(int userId) {
		// TODO Auto-generated method stub
		return addressRepository.getAddressDefaultByUserId(userId);
	}

	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id).get();
	}

	@Override
	public void save(Address address) {
		// TODO Auto-generated method stub
		addressRepository.save(address);
	}
	
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public void delete(Address address) {
		// TODO Auto-generated method stub
		addressRepository.delete(address);
	}

}
