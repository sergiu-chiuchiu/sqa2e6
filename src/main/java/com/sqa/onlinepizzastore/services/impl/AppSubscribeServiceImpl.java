package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.repositories.AppSubscribeRepository;
import com.sqa.onlinepizzastore.services.AppSubscribeService;

@Service
public class AppSubscribeServiceImpl implements AppSubscribeService {

	@Autowired
	AppSubscribeRepository appSubscribeRepository;
	
	@Override
	public AppSubscribe saveAppSubscribe(AppSubscribe appSubscribe) {
		return appSubscribeRepository.save(appSubscribe);
	}

}
