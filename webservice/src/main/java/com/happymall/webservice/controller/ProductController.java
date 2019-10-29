package com.happymall.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happymall.webservice.domain.Product;
import com.happymall.webservice.service.ProductService;

@RestController
@RequestMapping({ "/products" })
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "", "/all" })
	public List<Product> list(Model model) {
		List<Product> list = productService.getAllProducts();

		return list;
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
	@ResponseStatus(value = HttpStatus.OK)
	public Product processAddNewProductForm(@RequestBody Product productToBeAdded) {
//		System.out.println("##############################################################################################");
//		System.out.println(productToBeAdded.getName()+": "+ productToBeAdded.getDescription()+": "+ productToBeAdded.getPrice()+": "+productToBeAdded.getQuantity());
		productService.addProduct(productToBeAdded);
		return productToBeAdded;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Product processUpdateProductForm(@RequestBody Product productToBeUpdated) {

		return productService.updateProduct(productToBeUpdated);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteProduct(@RequestBody Product productToBeDeleted) {

		productToBeDeleted.setStatus("D");
		productService.updateProduct(productToBeDeleted);
	}

	@RequestMapping(value = "/approve", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Product approveProduct(@RequestBody Product product) {

		return productService.approveProduct(product);
	}

	@RequestMapping(value = "/block", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Product blockProduct(@RequestBody Product product) {

		return productService.blockProduct(product);
	}

	@RequestMapping("/by/vendor/{id}")
	public List<Product> getProductsByVendorId(@PathVariable("id") int id) {

		return productService.getProductsByVendorId(id);
	}

}
