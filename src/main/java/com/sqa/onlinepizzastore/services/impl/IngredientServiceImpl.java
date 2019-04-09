package com.sqa.onlinepizzastore.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.repositories.IngredientRepository;
import com.sqa.onlinepizzastore.services.IngredientService;


@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	IngredientRepository ingredientRepository;
	
	/*to save an ingredient*/
	@Override
	public Ingredient save(Ingredient ing) {
		return ingredientRepository.save(ing);
	}
	
	
	/* search all ingredients*/
	@Override
	public List<Ingredient> findAll(){
		return ingredientRepository.findAll();
	}
	
	
	/*get an ingredient by id*/
	@Override
	public Ingredient findOne(Integer id) {
		return ingredientRepository.findById(id).orElse(null);
	}
	
	
	/*delete an ingredient*/
	@Override
	public void delete(Ingredient ing) {
		ingredientRepository.delete(ing);
	}
	
	@Override
	public List<Ingredient> findAllSortedAsc(String sortColumn) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		return ingredients;
	}

}
