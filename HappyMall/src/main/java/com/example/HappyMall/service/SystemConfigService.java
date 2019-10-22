package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.SystemConfig;

public interface SystemConfigService {

	void save (SystemConfig systemConfig);
	
	SystemConfig getToApplied();
	
	List<SystemConfig> getAll ();
}
