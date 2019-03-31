package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppProduct;

@Repository
public interface AppProductRepository extends JpaRepository<AppProduct, Long> {

//	AppProduct getAppProductById(Long prodId);
	
//	AppProduct getAppProductByName(String prodName);
	
//	@Query("SELECT ar FROM AppRole ar JOIN ar.users WHERE email = :email")
//	List<AppRole> getAppRoleByAppUserEmail(@Param("email") String email);
}
