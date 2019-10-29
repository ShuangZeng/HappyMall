package com.example.HappyMall.rest;

//import java.nio.charset.Charset;
import java.util.Collections;

//import org.apache.commons.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//import edu.mum.domain.UserCredentials;
//import edu.mum.service.UserCredentialsService;
//import edu.mum.service.impl.UserCredentialsServiceImpl;

@Component
public class RestHttpHeader {
	protected RestTemplate restTemplate;

//	@Autowired
//	UserCredentialsService userCredentialsService;

	public RestHttpHeader() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/*
	 * Set up authentication header PLUS JSON Accept header
	 */
	public HttpHeaders getHttpHeaders() {

//        String authHeader =(new Authenticator()).authenticate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//		requestHeaders.set("Authorization", authHeader);
		return requestHeaders;
	}

	public HttpEntity<?> getHttpEntity() {
		return new HttpEntity(getHttpHeaders());
	}

}
