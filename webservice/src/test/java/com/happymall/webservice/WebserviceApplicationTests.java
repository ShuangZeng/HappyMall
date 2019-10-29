package com.happymall.webservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.happymall.webservice.vendor.VendorControllerTests;
import com.happymall.webservice.vendor.VendorServiceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ VendorServiceTests.class, VendorControllerTests.class

})
@SpringBootTest
public class WebserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
