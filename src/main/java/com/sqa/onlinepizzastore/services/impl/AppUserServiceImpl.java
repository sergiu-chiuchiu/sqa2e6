package com.sqa.onlinepizzastore.services.impl;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.dto.AppUserDto;
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

	@Override
	public void createDefaultAdmin() throws ParseException {
		
		if (this.getAppUserByEmail("admin@gmail.com") == null) {
			// Creating Admin role
			AppRole appRole = new AppRole();
			appRole.setRoleName("ROLE_ADMIN");
			// Creating Admin 
			AppUser appUser = new AppUser();
			appUser.addAppRole(appRole);
			appUser.setEmail("admin@gmail.com");
			appUser.setPassword(EncryptedPasswordUtils.encryptPassword("admin"));
			appUser.setGender("M");
			appUser.setUserName("Admin");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d = sdf.parse("04/12/2003");
			appUser.setBirthDate(d);
			
			appUserRepository.save(appUser);
		}
	}
	
	@Override
	public void createDefaultOperator() throws ParseException {
		
		if (this.getAppUserByEmail("operator@gmail.com") == null) {
			// Creating Oper role
			AppRole appRole = new AppRole();
			appRole.setRoleName("ROLE_OPERATOR");
			// Creating Oper
			AppUser appUser = new AppUser();
			appUser.addAppRole(appRole);
			appUser.setEmail("operator@gmail.com");
			appUser.setPassword(EncryptedPasswordUtils.encryptPassword("admin"));
			appUser.setGender("M");
			appUser.setUserName("Operator");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date d = sdf.parse("22/06/1994");
			appUser.setBirthDate(d);
			
			appUserRepository.save(appUser);
		}
	}
	
	@Override
	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll();
	}
	
	@Override
	public AppUser savePrivilegedAppUser(AppUser appUser, String roleName) {
		AppRole appRole = appRoleService.getAppRoleByRoleName(roleName);
		if ( appRole == null) {
			appRole = new AppRole();
			appRole.setRoleName(roleName);
			// this might not be necessary
			appRoleService.saveAppRole(appRole);
		}
		appUser.addAppRole(appRole);
		
		//Encrypt password before persist
		String encryptedPass = EncryptedPasswordUtils.encryptPassword(appUser.getPassword()); 
		appUser.setPassword(encryptedPass);
		
		return appUserRepository.save(appUser);
	}
	
}
