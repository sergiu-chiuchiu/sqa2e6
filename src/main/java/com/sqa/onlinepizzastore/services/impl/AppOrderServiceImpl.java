package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sqa.onlinepizzastore.entitites.AppOrder;
import com.sqa.onlinepizzastore.repositories.AppOrderRepository;
import com.sqa.onlinepizzastore.services.AppOrderService;

@Service
public class AppOrderServiceImpl implements AppOrderService {

	@Autowired
	AppOrderRepository appOrderRepository;

	@Override
	public AppOrder saveAppOrder(AppOrder appOrder) {		
		return appOrderRepository.save(appOrder);
	}
	
	
}
