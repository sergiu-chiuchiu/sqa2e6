package com.sqa.onlinepizzastore.services;

import java.util.List;
import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.AppUser;

public interface AppProductService {


	
	AppProduct save(AppProduct appProductToSave);
	AppProduct update(AppProduct appProductToUpdate);
	List<AppProduct> findAll();
	AppProduct findOne(Long Id);
	void delete(AppProduct appProductToDelete);
	List<AppProduct> getAllProducts();
	
	AppProduct getAppProductByProductName(String productName);
//	AppProduct saveAppProduct(AppProduct appProductToSave);
	AppProduct findOne(String productName);

//	AppProduct getAppProductById(Long prodId);
//
//	AppProduct getAppProductByName(String prodName);
}
