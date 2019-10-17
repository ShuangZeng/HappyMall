/**
* The @Controller annotation sets my VendorController class as a Web MVC controller, 
* and the @RequestMapping annotation will map all server requests for the home page 
* to run this method.
* @author Anne Guimaraes - 986742
*/

package com.example.HappyMall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.ProductService;
import com.example.HappyMall.service.UserService;

@Controller

public class VendorController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/admin/vendor")
	public String listVendors(Model model, HttpSession session) {		
		//List<Product> products = productService.findProductsByVendor(session.getAttribute("id").toString());
		List<Product> products = productService.findProductsByVendor(1005);
		model.addAttribute("productList",products);
		return "vendor"; 
	}

}

