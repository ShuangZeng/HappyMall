package com.example.HappyMall.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.service.SystemConfigService;

//ThaoDao created and edited
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
	
	@RequestMapping(value="/admin/systemconfig/save", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String save (@Valid @RequestBody SystemConfig systemConfig, BindingResult result)
	{
		if (!result.hasErrors()) {
			System.out.println("Save systemConfig: " + systemConfig);
			if(systemConfig.getCreateDate() == null)
				systemConfig.setCreateDate(new Date());
			systemConfig.setModifiedDate(new Date());
			
			systemConfigService.save(systemConfig);
		}
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
