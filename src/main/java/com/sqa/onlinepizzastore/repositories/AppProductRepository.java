package com.sqa.onlinepizzastore.repositories;

import java.util.List;

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
	
	@Query(value = "SELECT * FROM APP_PRODUCT WHERE TYPE='Pizza'" , nativeQuery = true)
	  List<AppProduct> findPizzas();
	
	@Query(value = "SELECT * FROM APP_PRODUCT WHERE TYPE='Dip'" , nativeQuery = true)
	  List<AppProduct> findDips();
	
	@Query(value = "SELECT * FROM APP_PRODUCT WHERE TYPE LIKE 'Drink%'" , nativeQuery = true)
	  List<AppProduct> findDrinks();
	
	@Query(value = "SELECT * FROM APP_PRODUCT WHERE TYPE LIKE 'DrinkS%'" , nativeQuery = true)
	  List<AppProduct> findSodas();
	
	@Query(value = "SELECT * FROM APP_PRODUCT WHERE TYPE LIKE 'DrinkC%'" , nativeQuery = true)
	  List<AppProduct> findCocktails();
}
