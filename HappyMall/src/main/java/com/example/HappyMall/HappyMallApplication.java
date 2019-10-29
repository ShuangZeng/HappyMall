package com.example.HappyMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.HappyMall" })
public class HappyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyMallApplication.class, args);
	}

}
