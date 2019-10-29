package com.example.HappyMall.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class PaymentMgmt {
	@RequestMapping(value = "/admin/paymentmgmt")
	public String getShoppingCart() {
		return "paymentMgmt";
	}
}
