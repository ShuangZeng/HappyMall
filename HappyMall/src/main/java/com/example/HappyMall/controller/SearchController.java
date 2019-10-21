package com.example.HappyMall.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.service.ProductService;

@Controller
@RequestMapping({ "/search" })
public class SearchController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/searchProducts")
	public String searchProduct(Model model) {
		model.addAttribute("product", new Product());
		return "searchProducts";
	}

	@GetMapping(value = "/searchResult")
	public String searchResult(Model model, @RequestParam String productName) {
		model.addAttribute("productList",
				productService.getProductsByName(productName).stream().filter(p -> p.getQuantity() != 0)
						.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList()));
		return "searchResult";
	}

//	@GetMapping(value = "/adsearch")
//	public String showAdSearchForm(ModelMap modelMap) {
//		modelMap.addAttribute("product", new Product());
//		return "/search/findProducts";
//	}

}