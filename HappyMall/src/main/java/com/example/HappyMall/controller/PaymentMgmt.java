package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentMgmt {
	@RequestMapping(value = "/admin/paymentmgmt")
	public String getShoppingCart()
	{
		return "paymentMgmt";
	}
}
