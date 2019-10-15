package com.example.HappyMall.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomerLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
	
		String targetUrl = detemineTargetUrl(authentication);
		
		if(response.isCommitted()) {
			return;
		}
		
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	//Fetch the roles from Authentication object
	protected String detemineTargetUrl(Authentication authentication) {
		
		String url = "/login? error = true";
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority a:authorities) {
			roles.add(a.getAuthority());
		}
		
		//check user role and decide the redirect URL
		
		if(roles.contains("ADMIN")) {
			url = "/admin";
		}else if(roles.contains("SITE_USER")) {
			url = "/home";
		}
		return url;
		
	}
}
