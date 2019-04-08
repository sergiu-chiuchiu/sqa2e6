package com.sqa.onlinepizzastore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppCart;

@Repository
public interface AppCartRepository extends JpaRepository<AppCart, Long> {

	@Query("SELECT ar FROM AppCart ar WHERE customer_email = :customer_email")
	List<AppCart> getAppCartByAppUserEmail(@Param("customer_email") String customer_email);
	
	AppCart getAppCartByCartNo(Long cartNo);
 
	@Query("SELECT ar FROM AppCart ar WHERE active = :active")
	AppCart getAppCartByActive(@Param("active") char active);
	
}
