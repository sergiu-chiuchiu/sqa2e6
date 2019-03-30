package com.sqa.onlinepizzastore.services;

import com.sqa.onlinepizzastore.entitites.AppUser;

public interface AppUserService {

	AppUser getUserByUserName(String userName);

	AppUser saveAppUserAsUser(AppUser appUserToSave);

}
