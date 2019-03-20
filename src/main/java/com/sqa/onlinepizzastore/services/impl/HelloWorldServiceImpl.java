package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.HelloWorld;
import com.sqa.onlinepizzastore.repositories.HelloWorldRepository;
import com.sqa.onlinepizzastore.services.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	@Autowired
	HelloWorldRepository helloWorldRepository;
	
	@Override
	public String helloWorldFromService() {
		return "service says hi!";
	}
	
	@Override
	public String helloWorldPost(HelloWorld helloWorld) {
		helloWorldRepository.save(helloWorld);
		return "saved to repo";
	}
}
