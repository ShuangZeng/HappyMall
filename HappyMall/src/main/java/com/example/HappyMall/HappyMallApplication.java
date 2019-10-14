package com.example.HappyMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@ComponentScan(basePackages={"com.example.HappyMall.controller"})
public class HappyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyMallApplication.class, args);
	}

}
