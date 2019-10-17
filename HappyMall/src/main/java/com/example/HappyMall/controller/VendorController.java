/**
* The @Controller annotation sets my VendorController class as a Web MVC controller, 
* and the @RequestMapping annotation will map all server requests for the home page 
* to run this method.
* @author Anne Guimaraes - 986742
*/

package com.example.HappyMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VendorController {
	@RequestMapping(value = "/vendor")
	public String getShoppingCart()
	{
		return "vendor";
	}

}

