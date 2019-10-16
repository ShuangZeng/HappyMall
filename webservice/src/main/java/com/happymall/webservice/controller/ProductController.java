package com.happymall.webservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happymall.webservice.domain.Product;
import com.happymall.webservice.service.ProductService;



@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
 
 	@RequestMapping({"","/all"})
	public List<Product> list(Model model) {
		return  productService.getAllProducts();
 
	}
	
 	@RequestMapping("/{id}")
	public Product getProductById( @PathVariable("id") int productId) {

		return productService.getProduct(productId);
 	}

 	@RequestMapping("/name/{name}")
	public List<Product> getProductWithName( @PathVariable("name") String name) {

		return productService.getProductsByName(name);
 	}
 	
 	@RequestMapping("/vendor/{name}")
	public List<Product> getProductWithVendor( @PathVariable("name") String name) {

		return productService.getProductsByVendorName(name);
 	}

	   
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewProductForm(@RequestBody Product productToBeAdded ) {

			productService.addProduct(productToBeAdded);
 
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Product processUpdateProductForm(@RequestBody Product productToBeUpdated ) {

			return productService.updateProduct(productToBeUpdated);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteProduct(@PathVariable("id") int productId ) {

			productService.deleteProduct(productId);
	}
	
	

}
