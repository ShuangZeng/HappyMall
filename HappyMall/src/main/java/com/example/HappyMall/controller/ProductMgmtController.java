package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductMgmtController {
	@RequestMapping(value = "/admin/productmgmt")
	public String getProductMgmt()
	{
		return "productMgmt";
	}
}
