package com.sqa.onlinepizzastore.services;

import java.util.List;

import com.sqa.onlinepizzastore.entitites.Ingredient;

public interface IngredientService {

	Ingredient save(Ingredient ing);
	List<Ingredient> findAll();
	Ingredient findOne(Integer id);
	void delete(Ingredient ing);
	
}
