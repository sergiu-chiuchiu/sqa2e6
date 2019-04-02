package com.sqa.onlinepizzastore.services;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import com.sqa.onlinepizzastore.entitites.AppUser;

public interface AppUserService {

	AppUser saveAppUserAsUser(AppUser appUserToSave);

	AppUser getAppUserByEmail(String email);

	AppUser getAppUserByUserName(String userName);

	AppUser updateAppUser(AppUser appUser);

	AppUser getLoggedInAppUserByPrincipal(Principal principal);

	void deleteAppUser(AppUser appUserToDelete);

	void createDefaultAdmin() throws ParseException;

	void createDefaultOperator() throws ParseException;

	List<AppUser> getAllUsers();

	AppUser savePrivilegedAppUser(AppUser appUser, String roleName);

}
