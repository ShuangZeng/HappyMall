package com.example.HappyMall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.HappyMall.formatter.RoleFormatter;
import com.example.HappyMall.repository.RoleRepository;
import com.example.HappyMall.repository.UserRepository;
import com.example.HappyMall.service.UserService;
import com.example.HappyMall.serviceImpl.UserServiceImpl;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
//	@Autowired
//    UserRepository userRepository;
//	@Autowired
//    RoleRepository roleRepository;
//	@Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;	
	@Autowired
	RoleFormatter roleFormatter;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
//    @Bean
//    public UserService userService() {
//    	return new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder);
//    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(roleFormatter);
    }
}
