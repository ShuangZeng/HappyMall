package com.example.HappyMall.controller;

import com.example.HappyMall.domain.Address;
import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.Role;
import com.example.HappyMall.domain.User;
import com.example.HappyMall.service.AddressService;
import com.example.HappyMall.service.OrdersService;
import com.example.HappyMall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;



@Controller
public class RegisterController {
	@Autowired
	UserService userService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setActive_Ind('P');
        
        
        modelAndView.addObject("user", user);
        List<Role> roleList = new ArrayList<>();
        
        Role role = new Role();
        role.setId(1);
        role.setRole("End User");
        System.out.println(role);
        roleList.add(role);
        
        Role role1 = new Role();
        role1.setId(2);
        role1.setRole("Vendor");
        roleList.add(role1);
        System.out.println(roleList);
        
        Role role2 = new Role();
        role2.setId(3);
        role2.setRole("Customer");
        roleList.add(role2);
        System.out.println(roleList);
        
        
        modelAndView.addObject("roleList", roleList);
        
        modelAndView.setViewName("register");
        return modelAndView;
    }
		
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            //modelAndView.setViewName("register");
        	System.out.println(bindingResult.getAllErrors().get(0).toString());
        	modelAndView.addObject("successMessage", "Please correct the errors in form! ");
        	modelMap.addAttribute("bindingResult",bindingResult);
        } else {
        	
        	System.out.println(user);
        	int roleId = user.getRole().getId();
            
            if(roleId == 1 || roleId == 2) {
            	user.setActive_Ind('A');
            }else if(roleId ==3) {
            	user.setActive_Ind('U');
            }
            user.setCreateDate(new Date());

            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            
            //Create a new order
			Address address = addressService.getAddressDefaultByUserId(user.getId());
			Orders orders = new Orders(user, "", address, address, "ShoppingCart");
			ordersService.addOrder(orders);
////
            ModelAndView mv = new ModelAndView("redirect:/login");
            return mv;

        }
        return modelAndView;
    }

	
	@GetMapping("/success")
	public String success() {
		return "saveSuccess";
	}
	

}
