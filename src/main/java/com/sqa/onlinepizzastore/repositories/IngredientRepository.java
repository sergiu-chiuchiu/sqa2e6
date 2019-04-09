package com.sqa.onlinepizzastore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sqa.onlinepizzastore.entitites.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	
//	@Query(value = "SELECT i FROM Ingredient i ORDER BY ?columnName")
//    List<Ingredient> getSortedIngredients(@Param("columnName") String columnName);
	
}
