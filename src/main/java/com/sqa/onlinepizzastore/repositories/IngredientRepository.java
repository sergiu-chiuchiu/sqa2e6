package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sqa.onlinepizzastore.entitites.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	
}