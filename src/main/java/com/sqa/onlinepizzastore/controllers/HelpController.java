package com.sqa.onlinepizzastore.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqa.onlinepizzastore.entitites.HelpForm;

@Controller
public class HelpController {
	
@RequestMapping(value="/help", method=RequestMethod.GET)
public String getHelpForm() {
	return "Help";
}

@RequestMapping(value="/help", method=RequestMethod.POST)
public String help(@ModelAttribute(name="helpForm") HelpForm helpForm, Model model) {
	String email = helpForm.getEmail();
	String password = helpForm.getPassword();
	String option = helpForm.getOption();
	String message = helpForm.getMessage();
	if("admin".equals(email) && "admin".equals(password)) {
		return "Your message was sent!";
	
	}
	model.addAttribute("invalidCredentials", true);
	return "You don't complete all the fields!";
}
}
