package com.sqa.onlinepizzastore.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.Mail;
import com.sqa.onlinepizzastore.entitites.SubscribeForm;
import com.sqa.onlinepizzastore.services.AppSubscribeService;

@Controller
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
	public String getIndexForm(Model model) {
		AppSubscribe appSubscribe = new AppSubscribe();
		model.addAttribute("AppSubscribe", appSubscribe);
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String saveNewEmail(@ModelAttribute(value="AppSubscribe") AppSubscribe appSubScribe1) throws AddressException, MessagingException {
	
		AppSubscribe appSubscribe = new AppSubscribe();
		appSubscribe.setEmail(appSubScribe1.getEmail());
		appSubscribeService.saveAppSubscribe(modelMapper.map(appSubScribe1, AppSubscribe.class));
		Mail mail = new Mail();
		mail.Send("sergiu.chiuchiu@gmail.com", "sggd", "dgfgfd");
		
		return "index";
	}
	
}
