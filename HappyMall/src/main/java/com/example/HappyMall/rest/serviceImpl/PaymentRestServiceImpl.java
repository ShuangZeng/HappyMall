package com.example.HappyMall.rest.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.HappyMall.domain.Payment;
import com.example.HappyMall.domain.Product;
import com.example.HappyMall.rest.RestHttpHeader;
import com.example.HappyMall.rest.service.PaymentRestService;

public class PaymentRestServiceImpl implements PaymentRestService {
	
	@Autowired
	RestHttpHeader restHelper;

	@Value( "${base.url}" )
	private String baseUrl;
	String serviceUrl = "http://localhost:8000/payments";
	String serviceUrlExtended = serviceUrl + "/";

	@Override
	public List<Payment> getAllPayments() {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Payment[]> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, Payment[].class);	
 		List<Payment> paymentList = Arrays.asList(responseEntity.getBody());
		return paymentList;
	}

	@Override
	public Payment getPayment(int id) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Payment> responseEntity = restTemplate.exchange(serviceUrlExtended + id, HttpMethod.GET, httpEntity, Payment.class);	
		Payment payment = responseEntity.getBody();
		return payment;
	}

	@Override
	public Payment addPayment(Payment payment) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Payment> httpEntity = new HttpEntity<Payment>(payment, restHelper.getHttpHeaders());
		restTemplate.exchange(serviceUrl, HttpMethod.POST, httpEntity, Payment.class);	
		return payment;
	}

	@Override
	public Payment updatePayment(Payment payment) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Payment> httpEntity = new HttpEntity<Payment>(payment, restHelper.getHttpHeaders());
		payment = restTemplate.patchForObject(serviceUrlExtended + "update", httpEntity, Payment.class);
		return payment;
	}

	@Override
	public void deletePayment(Payment payment) {
		
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Payment> httpEntity = new HttpEntity<Payment>(payment, restHelper.getHttpHeaders());
		payment = restTemplate.patchForObject(serviceUrlExtended + "delete", httpEntity, Payment.class);
		
	}

	@Override
	// Customer CardDetail id
	public List<Payment> getCustomerPayments(int id) {
		
		String url = serviceUrlExtended + "customer/" + id;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Payment[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Payment[].class);	
		List<Payment> payments = Arrays.asList(responseEntity.getBody());
		return payments;
	}

	@Override
	public Payment getPaymentByOrderId(int id) {
		
		String url = serviceUrlExtended + "order/" + id;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Payment> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Payment.class);	
		Payment payment = responseEntity.getBody();
		return payment;
	}

	@Override
	public Payment approvePayment(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment refundMoney(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
