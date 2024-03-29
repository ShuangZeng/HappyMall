package com.example.HappyMall.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Role;
import com.example.HappyMall.repository.RoleRepository;
import com.example.HappyMall.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role get(Integer roleID) {
		return roleRepository.getOne(roleID);
	}
}
