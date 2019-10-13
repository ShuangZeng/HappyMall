package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductMgmt {
	@RequestMapping(value = "/admin/productmgmt")
	public String getShoppingCart()
	{
		return "productMgmt";
	}
}
