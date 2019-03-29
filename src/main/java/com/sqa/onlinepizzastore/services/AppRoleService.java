package com.sqa.onlinepizzastore.services;

import java.util.List;

import com.sqa.onlinepizzastore.entitites.AppRole;

public interface AppRoleService {


	AppRole saveAppRole(AppRole appRole);


	List<AppRole> getAppRoleByAppUserEmail(String email);


	AppRole getAppRoleByRoleName(String roleName);
	
}
