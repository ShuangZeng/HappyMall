package com.example.HappyMall.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.SystemConfig;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.UserService;

@Controller
@SessionAttributes({"user"})
public class EditAcountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = "/account")
	public String getAccount(Model model,HttpSession session){
		User user = (User)model.asMap().get("user");
		List<Address> listAddress = null;
		try {
			listAddress = addressService.findByUserId(user.getId());	
			model.addAttribute("listAddress", listAddress);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error fetching address");
		}
		
		return "editUser";		
	}
	
	@PostMapping(value = "/account/edit")
	public String editAccount(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
//		List<Address> addresses = user.getListAddress();
//		  for(Address address: addresses){
//		   addressService.saveAddress(address);
//		  }
		userService.saveUser(user);
		
//		System.out.print("address:"+ user.getListAddress());
		return "redirect:/account";
	}
	

	
	@GetMapping("/account/address/getone")
	@ResponseBody
	public Address getOne(int id)
	{
		System.out.println("Get address for Edit");
		return addressService.getAddress(id);
	}
	
	@PostMapping("/account/address/save")
	public String save (Address address, Model model)
	{
		System.out.println("Save address: " + address + "- Id: " + address.getId());
		User user = (User)model.asMap().get("user");
		if(address.getUser() == null)
			address.setUser(user);
		addressService.save(address);
		
		return "redirect:/account";
	}
	
	@GetMapping("/account/address/delete")
	public String delete (Address address)
	{
		System.out.println("Detele address");
		addressService.delete(address);
		
		return "redirect:/account";
	}
}
