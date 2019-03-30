package com.sqa.onlinepizzastore.services;

import com.sqa.onlinepizzastore.entitites.AppUser;

public interface AppUserService {


	AppUser saveAppUserAsUser(AppUser appUserToSave);

	AppUser getAppUserByEmail(String email);

	AppUser getAppUserByUserName(String userName);

	AppUser updateAppUser(AppUser appUser);

}
