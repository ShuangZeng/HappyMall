package com.example.HappyMall.rest.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.HappyMall.domain.Orders;
import com.example.HappyMall.domain.Product;
import com.example.HappyMall.rest.service.OrderRestService;

import com.example.HappyMall.rest.RestHttpHeader;

@Service
@Transactional
public class OrderRestServiceImpl implements OrderRestService {

	@Autowired
	RestHttpHeader restHelper;

	@Value("${base.url}")
	private String baseUrl;
	String serviceUrl = "http://localhost:8000/orders";
	String serviceUrlExtended = serviceUrl + "/";

	// -----------------------------------------------------------------------------------------
	// Create-----------------------------------------------------------------------------------
	// All creating order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void addOrder(Orders order) {
		serviceUrlExtended = serviceUrl + "/addNew";
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		restTemplate.postForObject(serviceUrlExtended, httpEntity, Orders.class);
		//ResponseEntity<Orders> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders.class);	
 		//Orders order = responseEntity.getBody();
		//return order;
	}
	
	@Override
	public void addWithSendingEmail(Orders order) {
		serviceUrlExtended = serviceUrl + "/addNewWithNotification";
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		ResponseEntity<Orders> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders.class);	
 		//Orders order = responseEntity.getBody();
		//return order;
	}
	
	@Override
	public void sendNotification(Orders order) {
		serviceUrlExtended = serviceUrl + "/sendNotification";
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		ResponseEntity<Orders> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders.class);	
 		//Orders order = responseEntity.getBody();
		//return order;
	}
	
	// End Create-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Retrieve---------------------------------------------------------------------------------
	// All retrieving/getting order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public Orders getOrder(int id) {
		serviceUrlExtended = serviceUrl + "/" + id;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Orders> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders.class);	
 		Orders order = responseEntity.getBody();
		return order;
	}

	@Override
	public List<Orders> getAllOrders() {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Orders[]> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, Orders[].class);	
 		List<Orders> orderList = Arrays.asList(responseEntity.getBody());
		return orderList;
	}

	@Override
	public List<Orders> getAllOrdersByUser(int userId, boolean isEnduser) {
		serviceUrlExtended = serviceUrl + "/user?id=" + userId + "&isEnduser=" + isEnduser;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Orders[]> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders[].class);	
 		List<Orders> orderList = Arrays.asList(responseEntity.getBody());
		return orderList;
	}

	// End Retrieve-----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Update-----------------------------------------------------------------------------------
	// All updating/modifying order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void updateOrder(Orders order) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		restTemplate.patchForObject(serviceUrlExtended + "update", httpEntity, Orders.class);
	}
	
	@Override
	public void refundOrder(int orderId, int vendorId) {
		//RestTemplate restTemplate = restHelper.getRestTemplate();
		//HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		//restTemplate.patchForObject(serviceUrlExtended + "refund", httpEntity, Orders.class);
	}
	
	@Override
	public Orders requestToRefundOrder(int orderId) {
		serviceUrlExtended = serviceUrl + "/" + orderId;
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity httpEntity = new HttpEntity(restHelper.getHttpHeaders());
		ResponseEntity<Orders> responseEntity = restTemplate.exchange(serviceUrlExtended, HttpMethod.GET, httpEntity, Orders.class);	
 		Orders order = responseEntity.getBody();
		return order;
	}

	// End Update-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------

	
	
	// -----------------------------------------------------------------------------------------
	// Delete-----------------------------------------------------------------------------------
	// All deleting/removing order functions will be declared here
	// -----------------------------------------------------------------------------------------

	@Override
	public void deleteOrder(Orders order) {
		RestTemplate restTemplate = restHelper.getRestTemplate();
		HttpEntity<Orders> httpEntity = new HttpEntity<Orders>(order, restHelper.getHttpHeaders());
		restTemplate.patchForObject(serviceUrlExtended + "delete", httpEntity, Orders.class);
	}

	// End Delete-------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------
}