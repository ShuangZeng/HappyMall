package com.example.HappyMall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/")
	public String listProducts(Model model) {
		// get the product list by vendor
		return "product/productsList";
	}

	@GetMapping(value = { "/updateProduct", "/updateProduct/{id}" })
	public String updateProduct(Model model, @PathVariable(required = false, name = "id") Integer id) {
		//updating product
		return "product/updateProduct";
	}
	
	@GetMapping(value = "/allproducts")
	public String getAllProducts(Model model) {
		System.out.println("Get All products for admin..");
		model.addAttribute("productsList", productService.findAllProducts());
		return "product/allProducts";
	}
}