package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppOrder;

@Repository
public interface AppOrderRepository extends JpaRepository<AppOrder, Long>{
//	@Query(
//			  value = "SELECT * FROM USERS u WHERE u.status = 1", 
//			  nativeQuery = true)
	
}
