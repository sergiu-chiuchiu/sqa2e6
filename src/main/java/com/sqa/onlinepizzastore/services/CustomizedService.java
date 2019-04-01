package com.sqa.onlinepizzastore.services;

import java.util.List;

import org.springframework.validation.Errors;

import com.sqa.onlinepizzastore.entitites.Customized;


public interface CustomizedService {
	Customized save(Customized saveCust);
	Customized update(Customized updateCust);
	List<Customized> findAll();
	Customized findOne(Integer id);
	void delete(Integer id);
	Boolean customizedExists(Integer id);
 }

