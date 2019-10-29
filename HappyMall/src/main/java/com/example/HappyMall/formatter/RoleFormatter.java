package com.example.HappyMall.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.example.HappyMall.domain.Role;
import com.example.HappyMall.service.RoleService;

@Component
public class RoleFormatter implements Formatter<Role> {

	@Autowired
	RoleService roleService;

	@Override
	public String print(Role role, Locale locale) {
		return String.valueOf(role.getId());
	}

	@Override
	public Role parse(String roleID, Locale locale) throws ParseException {
//		System.out.println(roleID + "=====" + roleService.get());
		return roleService.get(Integer.parseInt(roleID));
	}

}
