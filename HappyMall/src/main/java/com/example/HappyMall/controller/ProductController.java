package com.example.HappyMall.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.service.ProductService;

@Controller
@RequestMapping({ "/products" })
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "", "/all" })
	public List<Product> list(Model model) {

		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public @ResponseBody Product getProductById(@PathVariable("id") int productId) {

		return productService.getProduct(productId);
	}

	@RequestMapping("/name/{name}")
	public List<Product> getProductWithName(@PathVariable("name") String name) {

		return productService.getProductsByName(name);
	}

	@RequestMapping("/vendor/{name}")
	public List<Product> getProductWithVendor(@PathVariable("name") String name) {

		return productService.getProductsByVendorName(name);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void processAddNewProductForm(@RequestBody Product productToBeAdded) {

		productService.addProduct(productToBeAdded);

	}
	
	@RequestMapping("/admin/newProduct")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);    
	    return "newProduct";
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    productService.addProduct(product);
	    return "redirect:/products/updateProducts";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Product processUpdateProductForm(@RequestBody Product productToBeUpdated) {

		return productService.updateProduct(productToBeUpdated);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteProduct(@RequestBody Product productToBeDeleted) {

		productToBeDeleted.setStatus("D");
		productService.updateProduct(productToBeDeleted);
	}
	
	@RequestMapping("admin/delete/")
	public String deleteProduct(@RequestParam Integer productId) {
	    productService.getProduct(productId).setStatus("D");
	    return "redirect:/products/updateProducts";
	}

	@GetMapping(value = "/admin/products")
	public String getProductsList(Model model, Authentication authentication) {
		model.addAttribute("productList", productService.getAllProducts().stream().filter(p -> p.getQuantity() != 0)
				.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList()));
		return "productsList";
	}

	@GetMapping(value = "/admin/update")
	public String getUpdateProducts(Model model, Authentication authentication) {
		model.addAttribute("productList", productService.getAllProducts());
		return "updateProducts";
	}

	@GetMapping(value = "/admin/editProduct")
	public String editProduct(Model model, Authentication authentication, @RequestParam Integer productId) {
		model.addAttribute("product", productService.getProduct(productId));
		return "editProduct";
	}

//	@PostMapping(value = "/admin/Edited")
//	public String edited(Model model, Authentication authentication) {
//		Product product = new Product();
//		productService.updateProduct(product);
//		return "redirect:/";
//	}

}
