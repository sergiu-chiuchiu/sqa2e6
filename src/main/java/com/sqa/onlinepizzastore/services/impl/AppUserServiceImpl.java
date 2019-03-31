package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppRole;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.repositories.AppUserRepository;
import com.sqa.onlinepizzastore.services.AppRoleService;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.EncryptedPasswordUtils;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleService appRoleService;
	

	@Override
	public AppUser getAppUserByEmail(String email) {
		return appUserRepository.getAppUserByEmail(email);

	}
	
	@Override
	public AppUser saveAppUserAsUser(AppUser appUserToSave) {
		String encryptedPass = EncryptedPasswordUtils.encryptPassword(appUserToSave.getPassword());
		appUserToSave.setPassword(encryptedPass);
		//Check if app role already exists if not create it
		AppRole appRole = appRoleService.getAppRoleByRoleName("ROLE_USER");
		if ( appRole == null) {
			appRole = new AppRole();
			appRole.setRoleName("ROLE_USER");
			appRoleService.saveAppRole(appRole);
		}

		appUserToSave.addAppRole(appRole);		
		return appUserRepository.save(appUserToSave);
	}
	
}
