package com.sqa.onlinepizzastore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sqa.onlinepizzastore.entitites.HelloWorld;
//import com.sqa.onlinepizzastore.entitites.HelloWorld;
import com.sqa.onlinepizzastore.services.HelloWorldService;

@Controller
public class HelloWorldController {
	@Autowired
	HelloWorldService helloWorldService;
	
	@GetMapping("/hello")
	public String hello() {
		helloWorldService.helloWorldFromService();
		return "HelloWorld";
	}
	
	@PostMapping("/postHello")
	public String postHelloWorld(@RequestBody HelloWorld helloWorld) {
		System.out.println("name: ");
		helloWorldService.helloWorldPost(helloWorld);
		return "helloWorldPost";
	}
}
