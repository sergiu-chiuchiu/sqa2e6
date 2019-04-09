package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.Customized;

public interface CustomizedRepository extends JpaRepository<Customized, Integer>{

	@Query("SELECT ar FROM Customized ar WHERE customized_name = :customized_name")
	Customized getCustomizedByCustomizedName(@Param("customized_name") String customized_name);

}
