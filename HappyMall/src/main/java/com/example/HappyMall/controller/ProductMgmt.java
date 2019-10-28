package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class ProductMgmt {
	@RequestMapping(value = "/admin/productmgmt")
	public String getProductManagment()
	{
		return "productMgmt";
	}
}
