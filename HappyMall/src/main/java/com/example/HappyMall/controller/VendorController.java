package com.example.HappyMall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.HappyMall.domain.Product;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.ProductService;
import com.example.HappyMall.service.VendorService;

@Controller
@SessionAttributes ({"user"})
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/admin/vendor")
	public String listVendors(Model model, HttpSession session) {
		User user = (User)model.asMap().get("user");
		System.out.print(user);
		List<Product> products = productService.findProductsByVendor(user.getId());
		//List<Product> products = productService.findProductsByVendor(1005);
		model.addAttribute("productList",products);
		return "vendor"; 
	}
	
	@RequestMapping({"","/all"})
	public List<User> list(Model model) {
		return  vendorService.getAllVendors();
 
	}
	
	@RequestMapping("/{id}")
	public User getProductById( @PathVariable("id") int vendorId) {

		return vendorService.getVendor(vendorId);
 	}
	
	@RequestMapping("/email/{email}")
	public User getVendorWithEmail( @PathVariable("email") String email) {

		return vendorService.getVendorByEmail(email);
 	}
	
	@RequestMapping("/phone/{phone}")
	public User getVendorWithPhone( @PathVariable("phone") String phone) {

		return vendorService.getVendorByPhone(phone);
 	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewVendorForm(@RequestBody User vendor ) {

			vendorService.addVendor(vendor);
 
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public User processUpdateVendorForm(@RequestBody User vendor ) {

			return vendorService.updateVendor(vendor);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteVendor(@RequestBody User vendor ) {

			vendorService.deleteVendor(vendor);
	}

}

