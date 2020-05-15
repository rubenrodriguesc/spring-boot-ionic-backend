package com.rubenrodrigues.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.rubenrodrigues.cursomc.security.UserSecuriry;

public class UserService {
	
	public static UserSecuriry authenticated() {
		try {
			return (UserSecuriry) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
