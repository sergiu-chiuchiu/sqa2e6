package com.sqa.onlinepizzastore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sqa.onlinepizzastore.entitites.HelpForm;

@Controller
public class HelpController {
@RequestMapping(value="/help", method=RequestMethod.GET)
public String getHelpForm() {
	return "help";
}
@RequestMapping(value="/help", method=RequestMethod.POST)
public String help(@ModelAttribute(name="helpForm") HelpForm helpForm, Model model) {
	String username = helpForm.getUsername();
	String password = helpForm.getPassword();
	if("admin".equals(username) && "admin".equals(password)) {
		return "help";
	
	}
	model.addAttribute("invalidCredentials", true);
	return "help";
}
}
