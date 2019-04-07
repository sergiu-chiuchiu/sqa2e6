package com.sqa.onlinepizzastore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.repositories.AppProductRepository;
import com.sqa.onlinepizzastore.services.AppProductService;

@Service
public class AppProductServiceImpl implements AppProductService {

	@Autowired
	AppProductRepository appProductRepository;
	
	@Override
	public AppProduct save(AppProduct appProductToSave) {
		return appProductRepository.save(appProductToSave);
	}
	
	@Override
	public AppProduct update(AppProduct appProductToUpdate) {
		return appProductRepository.save(appProductToUpdate);
	}
	@Override
	public List<AppProduct> findAll(){
		return appProductRepository.findAll();
	}
		
	@Override
	public AppProduct findOne(Long id) {
		return appProductRepository.findById(id).orElse(null);
	}
	
	
	@Override
	public void delete(AppProduct appProductToDelete) {
		appProductRepository.delete(appProductToDelete);
	}

	@Override
	public List<AppProduct> getAllProducts() {
		return appProductRepository.findAll();
	}

	@Override
	public AppProduct getAppProductByProductName(String productName) {
		return appProductRepository.getAppProductByProductName(productName);
	}
	
	
	
	
	
	
//	@Override
//	public AppProduct getAppProductById(Long prodId) {
//		return appProductRepository.getAppProductById(prodId);
//	}
//
//	@Override
//	public AppProduct getAppProductByName(String prodName) {
//		return appProductRepository.getAppProductByName(prodName);
//	}

}
