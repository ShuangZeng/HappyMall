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

import com.happymall.webservice.domain.Payment;
import com.happymall.webservice.service.PaymentService;

@RestController
@RequestMapping({"/payments"})
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
 
 	@RequestMapping({"","/all"})
	public List<Payment> list(Model model) {
 		
		return  paymentService.getAllPayments();
	}
 	
 	@RequestMapping("/customer/{id}")
	public List<Payment> getCustomerPayments(@PathVariable("id") int cardDetailId) {
 		
		return  paymentService.getCustomerPayments(cardDetailId);
	}
 	
 	@RequestMapping("/order/{id}")
	public Payment getPaymentByOrderId(@PathVariable("id") int orderId) {
 		
		return  paymentService.getPaymentByOrderId(orderId); 
	}
	
 	@GetMapping("/{id}")
	public @ResponseBody Payment getPaymentById( @PathVariable("id") int paymentId) {

		return paymentService.getPayment(paymentId);
 	}
 	
 	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void processAddNewPaymentForm(@RequestBody Payment paymentToBeAdded ) {

 		paymentService.addPayment(paymentToBeAdded);
 
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public Payment processUpdatePaymentForm(@RequestBody Payment paymentToBeUpdated ) {

			return paymentService.updatePayment(paymentToBeUpdated);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletePayment(@RequestBody Payment paymentToBeDeleted) {

		paymentToBeDeleted.setStatus("D");
		paymentService.updatePayment(paymentToBeDeleted);
	}
 	
 	
 	
 	

}
