package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.repositories.AppUserRepository;
import com.sqa.onlinepizzastore.services.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Override
	public AppUser getUserByUserName(String userName) {
		return appUserRepository.getAppUserByUserName(userName); 
	}
}
