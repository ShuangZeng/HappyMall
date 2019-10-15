package com.example.HappyMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HappyMall.domain.Payment;
import com.example.HappyMall.service.PaymentService;

@Controller
public class PaymentMgmtController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/admin/paymentmgmt")
	public String getPayment(Model model, Authentication authentication)
	{
		List<Payment> payment = paymentService.findAllPayment();
		model.addAttribute("PaymentList",payment);
		return "paymentMgmt";
	}
}
