package com.sqa.onlinepizzastore.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppUser;



@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	AppUser getAppUserByEmail(String email);

}
