package com.sqa.onlinepizzastore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppRole;
import com.sqa.onlinepizzastore.repositories.AppRoleRepository;
import com.sqa.onlinepizzastore.services.AppRoleService;

@Service
public class AppRoleServiceImpl implements AppRoleService {
	
	@Autowired
	AppRoleRepository appRoleRepository;

	@Override
	public List<AppRole> getAppRoleByAppUserEmail(String email) {
		return appRoleRepository.getAppRoleByAppUserEmail(email);
	}
	
	@Override
	public AppRole getAppRoleByRoleName(String roleName) {
		return appRoleRepository.getAppRoleByRoleName(roleName);
	}
	
	@Override
	public AppRole saveAppRole(AppRole appRole) {
		return appRoleRepository.save(appRole);
	}
	
}
