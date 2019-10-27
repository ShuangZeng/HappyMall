package com.happymall.webservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.happymall.webservice.domain.Product;
import com.happymall.webservice.domain.User;
import com.happymall.webservice.service.ProductService;
import com.happymall.webservice.service.VendorService;
import com.happymall.webservice.service.impl.ProductServiceImpl;
import com.happymall.webservice.service.impl.VendorServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTests {
	
	@TestConfiguration
	static class VendorServiceImplTestContextConfiguration {
		
		@Bean
		public ProductService productService() {
			return new ProductServiceImpl();
		}
		
		@Bean
		public Product product() {
			return new Product();
		}
	}
	
	@Autowired
	private ProductService productService;
	
	
	@Test
	public void whenGetAllProductsThenListNotEmpty() {
		Object products = productService.getAllProducts();
		assertThat(products).asList();
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) products;
		assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	public void whenGetProductsByNameThenReturnProducts() {
		String name = "Calico Critters Baby Nursery Set";
		List<Product> list = productService.getProductsByName(name);
		assertThat(list.get(0).getPrice()).isEqualTo(12.21);
	}
	
	@Test
	public void whenGetProductsByVendorNameThenReturnVendor() {
		String productName = "Kids Headphones - noot products K11 Foldable Stereo";
		String vendorName = "Brooks Griffin";
		List<Product> list = productService.getProductsByVendorName(vendorName);
		assertThat(list.get(0).getName()).isEqualTo(productName);
	}
	
	@Test
	public void whenGetProductByIdThenReturnProduct() {
		int id = 4009;
		String productName = "Kids Headphones - noot products K11 Foldable Stereo";
		Product product = productService.getProduct(id);
		assertThat(product.getName()).isEqualTo(productName);
	}

}
