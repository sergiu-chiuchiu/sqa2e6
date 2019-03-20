package com.sqa.onlinepizzastore.services;

import com.sqa.onlinepizzastore.entitites.HelloWorld;

public interface HelloWorldService {
	String helloWorldFromService();
	String helloWorldPost(HelloWorld helloWorld);
}
