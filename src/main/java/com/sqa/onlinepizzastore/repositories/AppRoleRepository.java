package com.sqa.onlinepizzastore.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	
}
