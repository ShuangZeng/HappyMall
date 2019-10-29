package com.happymall.webservice.vendor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VendorControllerTests {

	private final String ROOT_URI = "http://localhost:8000/vendors";

	@Test
	public void whenGetAllVendorsThenSuccess() {
		Response response = RestAssured.get(ROOT_URI);

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void whenGetVendorByIdThenSuccess() {
		Response response = RestAssured.get(ROOT_URI + "/1001");

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

	@Test
	public void whenGetVendorByEmailThenSuccess() {
		Response response = RestAssured.get(ROOT_URI + "/email/sonnen@msn.com");

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

	@Test
	public void whenGetVendorByPhoneThenSuccess() {
		Response response = RestAssured.get(ROOT_URI + "/phone/(389) 255-2383");

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

}
