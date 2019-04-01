package com.sqa.onlinepizzastore.controllers;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sqa.onlinepizzastore.services.AppRoleService;

@ControllerAdvice(annotations = Controller.class)
public class AnnotationAdvice {
	@Autowired
	AppRoleService appRoleService;
	
	@ModelAttribute("currentUserRole")
	public String getCurrentUserRole() {
		UserDetails userDetails = (UserDetails) 
		         SecurityContextHolder.getContext()
		               .getAuthentication().getPrincipal();
		
		String userRole = "";
		for (GrantedAuthority e: userDetails.getAuthorities()) {
			userRole = e.toString();
			   
		}
		return userRole;
	}
	
}
