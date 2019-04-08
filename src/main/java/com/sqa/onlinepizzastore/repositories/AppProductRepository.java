package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppProduct;

@Repository
public interface AppProductRepository extends JpaRepository<AppProduct, Long> {

	AppProduct getAppProductByProductId(Long prodId);
	

	@Query("SELECT ar FROM AppProduct ar WHERE productName = :productName")
	AppProduct getAppProductByProductName(String productName);
	
//	@Query("SELECT ar FROM AppRole ar JOIN ar.users WHERE email = :email")
//	List<AppRole> getAppRoleByAppUserEmail(@Param("email") String email);
}
