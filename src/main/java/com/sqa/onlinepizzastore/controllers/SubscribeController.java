package com.sqa.onlinepizzastore.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.SubscribeForm;
import com.sqa.onlinepizzastore.services.AppSubscribeService;


public class SubscribeController {

	private final AppSubscribeService appSubscribeService;
	private final ModelMapper modelMapper;

	@Autowired
	public SubscribeController(AppSubscribeService appSubscribeService, ModelMapper modelMapper) {
		super();
		this.appSubscribeService = appSubscribeService;
		this.modelMapper = modelMapper;
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndexForm() {
		return "index";
	}
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String subscribe(@ModelAttribute(name="indexForm") SubscribeForm subscribeForm, Model model) {
		
		String email = subscribeForm.getEmail();
		AppSubscribe appSubscribe = new AppSubscribe();
		appSubscribe.setEmail(email);
		appSubscribeService.saveAppSubscribe(modelMapper.map(email, AppSubscribe.class));
		if(email != null) {
		return "index";
	}
		return "index";
	}
}
