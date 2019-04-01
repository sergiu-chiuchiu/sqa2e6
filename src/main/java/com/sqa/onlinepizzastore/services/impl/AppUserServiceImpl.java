package com.sqa.onlinepizzastore.services.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
	public AppUser getAppUserByUserName(String userName) {
		return appUserRepository.getAppUserByUserName(userName);
	}
	
	@Override
	public AppUser updateAppUser(AppUser appUser) {
		String pass = appUser.getPassword();
		if (pass != null) {
			appUser.setPassword(EncryptedPasswordUtils.encryptPassword(pass));
		}
		return appUserRepository.save(appUser);
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
	
	@Override
	public AppUser getLoggedInAppUserByPrincipal(Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userName = loginedUser.getUsername();
		return this.getAppUserByUserName(userName);
	}
	
	@Override
	public void deleteAppUser(AppUser appUserToDelete) {
		appUserRepository.delete(appUserToDelete);
	}
	
}
