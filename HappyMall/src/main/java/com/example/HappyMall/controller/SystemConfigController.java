package com.example.HappyMall.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.service.SystemConfigService;

@Controller
public class SystemConfigController {

	@Autowired
	public SystemConfigService systemConfigService;
	
	@GetMapping("/admin/systemconfig")
	public String loadSystemConfig(Model model)
	{
		model.addAttribute("listSystemConfig", systemConfigService.getAll());
		model.addAttribute("checkedCreate", systemConfigService.getNewest() == null ? true : false);
		return "systemconfig";
	}
	
	@GetMapping("/admin/systemconfig/getone")
	@ResponseBody
	public SystemConfig getOne(int id)
	{
		return systemConfigService.getSystemConfig(id);
	}
	
	@PostMapping("/admin/systemconfig/save")
	public String save (SystemConfig systemConfig)
	{
		System.out.println("Save systemConfig");
		if(systemConfig.getCreateDate() == null)
			systemConfig.setCreateDate(new Date());
		
		systemConfigService.save(systemConfig);
		
		return "redirect:/admin/systemconfig";
	}
	
	@GetMapping("/admin/systemconfig/delete")
	public String delete (SystemConfig systemConfig)
	{
		System.out.println("Detele systemConfig");
		systemConfigService.delete(systemConfig);
		
		return "redirect:/admin/systemconfig";
	}
}
