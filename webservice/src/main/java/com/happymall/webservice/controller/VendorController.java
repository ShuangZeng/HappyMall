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

import com.happymall.webservice.domain.User;
import com.happymall.webservice.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@RequestMapping({"","/all"})
	public List<User> list(Model model) {
		return  vendorService.getAllVendors();
 
	}
	
	@RequestMapping("/{id}")
	public User getProductById( @PathVariable("id") UUID vendorId) {

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteVendor(@PathVariable("id") UUID vendorId ) {

			vendorService.deleteVendor(vendorId);
	}

}
