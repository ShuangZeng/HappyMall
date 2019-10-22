package com.example.HappyMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.HappyMall.service.SystemConfigService;

@Controller
public class SystemConfigController {

	@Autowired
	public SystemConfigService systemConfigService;
	
	@GetMapping("/admin/systemconfig")
	public String loadSystemConfig(Model model)
	{
		model.addAttribute("listSystemConfig", systemConfigService.getAll());
		return "systemconfig";
	}
	
	
}
