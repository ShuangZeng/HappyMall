package com.happymall.webservice.vendor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.domain.User;
import com.happymall.webservice.service.VendorService;
import com.happymall.webservice.service.impl.VendorServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VendorServiceTests {
	
	@TestConfiguration
	static class VendorServiceImplTestContextConfiguration {
		
		@Bean
		public VendorService vendorService() {
			return new VendorServiceImpl();
		}
		
		@Bean
		public User vendor() {
			return new User();
		}
	}
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private User vendor;
	
	@Test
	public void whenGetAllVendorsThenListNotEmpty() {
		Object vendors = vendorService.getAllVendors();
		assertThat(vendors).asList();
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) vendors;
		assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	public void whenGetVendorByEmailThenReturnVendor() {
		String email = "sonnen@msn.com";
		String expectedName = "Brooks Griffin";
		User vendor = vendorService.getVendorByEmail(email);
		assertThat(vendor.getFullName()).isEqualTo(expectedName);
	}
	
	@Test
	public void whenGetVendorByPhoneThenReturnVendor() {
		String phone = "(389) 255-2383";
		String expectedName = "Brooks Griffin";
		User vendor = vendorService.getVendorByPhone(phone);
		assertThat(vendor.getFullName()).isEqualTo(expectedName);
	}
	
	@Test
	public void whenGetVendorByIdThenReturnVendor() {
		int id = 1001;
		String expectedName = "Brooks Griffin";
		User vendor = vendorService.getVendor(id);
		assertThat(vendor.getFullName()).isEqualTo(expectedName);
	}
	
//	@Test
//	public void whenAddVendorThenGetByEmail() {
//		String email = "abc@def.com";
//		String expectedName = "Buba Bojang";
//		vendor.setFullName(expectedName);
//		vendor.setEmail(email);
//		vendor.setActive_Ind('A');
//		vendor.setUserName("biji");
//		vendor.setPassword("abc123");
//		
//		
//		User vendor = vendorService.getVendorByEmail(email);
//		assertThat(vendor.getFullName()).isEqualTo(expectedName);
//	}
	
	

}
