package com.sqa.onlinepizzastore.services;

import java.util.List;
import com.sqa.onlinepizzastore.entitites.AppProduct;

public interface AppProductService {


	
	AppProduct save(AppProduct appProductToSave);
	AppProduct update(AppProduct appProductToUpdate);
	List<AppProduct> findAll();
	AppProduct findOne(String productName);
	AppProduct findOne(Long id);
	void delete(AppProduct appProductToDelete);
	
	
//	AppProduct saveAppProduct(AppProduct appProductToSave);

//	AppProduct getAppProductById(Long prodId);
//
//	AppProduct getAppProductByName(String prodName);
}
