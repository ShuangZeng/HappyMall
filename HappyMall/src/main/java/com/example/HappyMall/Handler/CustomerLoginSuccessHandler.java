package com.example.HappyMall.Handler;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


import com.example.HappyMall.service.UserService;

@Configuration
public class CustomerLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	 UserService userService;
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		HttpSession session = request.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute("user", userService.findUserByEmail(authUser.getUsername()));
		String targetUrl = detemineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	// Fetch the roles from Authentication object
	protected String detemineTargetUrl(Authentication authentication) {

		String url = "/login? error = true";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		// check user role and decide the redirect URL

		if (roles.contains("4")) {
			url = "/admin";
		} else if (roles.contains("1")) {
			url = "/index";
		} else if (roles.contains("2")) {
			url = "/admin/vendor";
		} else if (roles.contains("3")) {
			url = "/customer";
		}
		return url;

	}
}
