package com.sqa.onlinepizzastore.services;

import java.util.List;
import java.util.Set;

import org.springframework.validation.Errors;

import com.sqa.onlinepizzastore.entitites.Customized;
import com.sqa.onlinepizzastore.entitites.Ingredient;


public interface CustomizedService {
	Customized save(Customized saveCust);
	Customized update(Customized updateCust);
	List<Customized> findAll();
	Customized findOne(Integer id);
	void delete(Customized cust);
	Boolean customizedExists(Integer id);
	Customized getCustomizedByCustomizedName(String customizedName);
	
 }

