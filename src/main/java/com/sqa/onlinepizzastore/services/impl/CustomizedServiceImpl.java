package com.sqa.onlinepizzastore.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sqa.onlinepizzastore.entitites.Customized;
import com.sqa.onlinepizzastore.repositories.CustomizedRepository;
import com.sqa.onlinepizzastore.services.CustomizedService;


@Service
public class CustomizedServiceImpl implements CustomizedService {

	@Autowired
	CustomizedRepository customizedRepository;
	
	/*to save an customized*/
	@Override
	public Customized save(Customized saveCust) {
		return customizedRepository.save(saveCust);
	}
	
	@Override
	public Customized update(Customized updateCust) {
		return customizedRepository.save(updateCust);
	}
	
	@Override
	public Boolean customizedExists(Integer id) {
		return customizedRepository.existsById(id);
	}
	
	
	/* search all customizeds*/
	@Override
	public List<Customized> findAll(){
		return customizedRepository.findAll();
	}
	
	
	/*get an customized by id*/
	@Override
	public Customized findOne(Integer id) {
		return customizedRepository.findById(id).orElse(null);
	}
	
	
	/*delete an customized*/
	@Override
	public void delete(Integer id) {
		customizedRepository.deleteById(id);
	}
	
}