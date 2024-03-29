package com.example.HappyMall.service;

import java.util.List;

import com.example.HappyMall.domain.SystemConfig;

//ThaoDao created and edited
public interface SystemConfigService {

	void save(SystemConfig systemConfig);

	SystemConfig getToApplied();

	List<SystemConfig> getAll();

	SystemConfig getNewest();

	SystemConfig getSystemConfig(int id);

	void delete(SystemConfig systemConfig);
}
