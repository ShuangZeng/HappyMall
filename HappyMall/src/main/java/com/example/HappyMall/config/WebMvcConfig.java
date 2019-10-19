package com.example.HappyMall.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.HappyMall.Handler.CustomerLoginSuccessHandler;
import com.example.HappyMall.formatter.RoleFormatter;
//import com.example.HappyMall.interceptor.WelcomeInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
		
	@Autowired
	RoleFormatter roleFormatter;
	
     
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(roleFormatter);
    }
    
}

