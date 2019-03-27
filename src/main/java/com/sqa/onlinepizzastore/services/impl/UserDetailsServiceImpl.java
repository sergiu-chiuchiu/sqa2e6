package com.sqa.onlinepizzastore.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.repositories.AppRoleRepository;
import com.sqa.onlinepizzastore.repositories.AppUserRepository;
import com.sqa.onlinepizzastore.services.AppUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	private AppUserRepository appUserRepository;
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	public UserDetailsServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
		super();
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = this.appUserRepository.getAppUserByUserName(email);
		
		if(appUser == null) {
			System.out.println("User not found! " + email);
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		System.out.println("Found user: " + appUser);
		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appUserRepository.getUser_RoleByEmail(email);
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if(roleNames != null) {
			for(String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), 
				appUser.getPassword(), grantList);
		return userDetails;
	}
	
	
	
}
