package com.example.HappyMall.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.repository.SystemConfigRepository;
import com.example.HappyMall.service.SystemConfigService;

//ThaoDao created and edited
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@Override
	public void save(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		systemConfigRepository.save(systemConfig);
	}

	@Override
	public List<SystemConfig> getAll() {
		// TODO Auto-generated method stub
		return systemConfigRepository.findAll();
	}

	@Override
	public SystemConfig getToApplied() {
		// TODO Auto-generated method stub
		return systemConfigRepository.getToApplied();
	}

	@Override
	public SystemConfig getNewest() {
		// TODO Auto-generated method stub
		return systemConfigRepository.getNewest();
	}

	@Override
	public SystemConfig getSystemConfig(int id) {
		// TODO Auto-generated method stub
		return systemConfigRepository.findById(id).get();
	}

	@Override
	public void delete(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		systemConfigRepository.delete(systemConfig);
	}

}
