package com.example.HappyMall.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.ProductService;

@Controller
@SessionAttributes("user")
@RequestMapping({ "/products" })
public class ProductController {

	@Autowired
	ServletContext context;
	
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
	public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("productImage") MultipartFile productImage,
			Model model) throws IOException {

		String rootDirectory = context.getRealPath("/HappyMall/src/main/resources/static/images/");
		System.out.println(rootDirectory);
		User user = (User) model.asMap().get("user");		
		if (productImage != null && !productImage.isEmpty()) { 
			try { 
				byte[] bytes = productImage.getBytes();
				productImage.transferTo( new File(rootDirectory + productImage.getOriginalFilename())); 
		        Path path = Paths.get(rootDirectory + productImage.getOriginalFilename());
		        Files.write(path, bytes);
				System.out.println("Image Transfered");
			} 
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("File not found");
			}
		}
		product.setImageUrl(rootDirectory + productImage.getOriginalFilename());
		System.out.println(rootDirectory + productImage.getOriginalFilename());
		product.setVendor(user);
		productService.addProduct(product);
		return "redirect:/vendor/";
	}

//	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
//	public String saveProduct(@ModelAttribute("product") Product product) {
//	    productService.addProduct(product);
//	    return "redirect:/products/admin/update";
//	}
//	
//	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
//    public void importParse(@RequestParam("myFile") MultipartFile myFile) {
//         // ... do whatever you want with 'myFile'
//         // Redirect to a successful upload page
////         return "redirect:products/admin/newProduct";
//    }

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product) {
		productService.updateProduct(product);
		return "redirect:/products/admin/update/";
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

	@GetMapping(value = "/admin/update/delete")
	public String deleteProduct(Model model, @RequestParam String productId) {
		Product p = new Product();
		p.setId(Integer.valueOf(productId));
		productService.deleteProduct(p);
		return "redirect:/products/admin/update";
	}

	@GetMapping(value = "/admin/products")
	public String getProductsList(Model model, Authentication authentication) {
		model.addAttribute("productList", productService.getAllProducts().stream().filter(p -> p.getQuantity() != 0)
				.filter(p -> !p.getStatus().equals("D")).collect(Collectors.toList()));
		return "productsList";
	}

	@GetMapping(value = "/admin/update")
	public String getUpdateProducts(Model model) {
		model.addAttribute("productList", productService.getAllProducts());
		return "updateProducts";
	}

	@GetMapping(value = "/admin/editProduct")
	public String editProduct(Model model, @RequestParam Integer productId) {
		model.addAttribute("product", productService.getProduct(productId));
		return "editProduct";
	}
}