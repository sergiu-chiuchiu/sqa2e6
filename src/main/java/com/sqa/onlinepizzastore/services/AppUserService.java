package com.sqa.onlinepizzastore.services;

import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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

	void sendPasswordResetEmail(AppUser appUser) throws AddressException, MessagingException, MalformedURLException;

	AppUser getAppUserByPasswordResetToken(String passwordResetToken);

}
