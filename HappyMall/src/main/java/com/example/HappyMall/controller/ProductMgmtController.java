package com.example.HappyMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.ProductService;

@Controller
public class ProductMgmtController {
	
	@Autowired
	private ProductService productService;


	@RequestMapping(value = "/admin/productmgmt")
	public String getShoppingCart(Model model, Authentication authentication)
	{
		List<Product> products = productService.findAllProducts();
		model.addAttribute("productList",products);
		return "productMgmt";
	}
	@GetMapping(value = "admin/productmgmt/approve")
	public String approveProduct(Model model, @RequestParam String productId){
		Product p = new Product();
		p.setId(Integer.valueOf(productId));
		productService.approveProduct(p);
//		System.out.print(productId);
		return "redirect:/admin/productmgmt";
	}
	@GetMapping(value = "admin/productmgmt/block")
	public String blcokProduct(Model model, @RequestParam String productId){
		Product p = new Product();
		p.setId(Integer.valueOf(productId));
		productService.blockProduct(p);
		System.out.print(productId);
		return "redirect:/admin/productmgmt";
	}
	
}