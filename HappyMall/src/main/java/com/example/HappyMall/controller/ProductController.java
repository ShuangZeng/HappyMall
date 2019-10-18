package com.example.HappyMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.service.ProductService;

@Controller
@RequestMapping
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "/admin/products")
	public String getProductsList(Model model, Authentication authentication) {
		model.addAttribute("productList", productService.findAllProducts());
		return "productsList";
	}


	@GetMapping(value = "/admin/update")
	public String getUpdateProducts(Model model, Authentication authentication) {
		model.addAttribute("productList", productService.findAllProducts());
		return "updateProducts";
	}
	
	@GetMapping(value = "/admin/editProduct")
	public String editProducts(Model model, Authentication authentication, @RequestParam Integer productId) {
		model.addAttribute("product", productService.getProduct(productId));
		return "editProduct";
	}
	
	@PostMapping(value = "/admin/Edited")
	public String edited(Model model, Authentication authentication, @RequestBody Product product) {
		productService.updateProduct(product);
		return "updateProducts";
	}
	
}