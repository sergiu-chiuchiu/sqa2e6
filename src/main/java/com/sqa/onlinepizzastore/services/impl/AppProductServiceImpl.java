package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.repositories.AppProductRepository;
import com.sqa.onlinepizzastore.services.AppProductService;

@Service
public class AppProductServiceImpl implements AppProductService {

	@Autowired
	AppProductRepository appProductRepository;
	
	@Override
	public AppProduct saveAppProduct(AppProduct appProductToSave) {
		return appProductRepository.save(appProductToSave);
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
