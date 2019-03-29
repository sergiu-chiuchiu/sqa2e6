package com.sqa.onlinepizzastore.services;

import com.sqa.onlinepizzastore.entitites.AppRole;

public interface AppRoleService {

	AppRole getAppRoleByRoleName(String roleName);

	AppRole saveAppRole(AppRole appRole);
	
}
