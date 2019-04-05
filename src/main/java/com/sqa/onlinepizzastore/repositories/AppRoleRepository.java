package com.sqa.onlinepizzastore.repositories;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{

	@Query("SELECT ar FROM AppRole ar JOIN ar.users WHERE email = :email")
	List<AppRole> getAppRoleByAppUserEmail(@Param("email") String email);
	
	@Query("SELECT ar FROM AppRole ar JOIN ar.users WHERE user_name = :userName")
	List<AppRole> getAppRoleByAppUserName(@Param("userName") String userName);
	
	AppRole getAppRoleByRoleName(String roleName);
	
}
