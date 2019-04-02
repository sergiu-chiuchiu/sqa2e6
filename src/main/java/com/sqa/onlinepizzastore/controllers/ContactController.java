package com.sqa.onlinepizzastore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sqa.onlinepizzastore.entitites.ContactForm;
import com.sqa.onlinepizzastore.entitites.HelpForm;

@Controller
public class ContactController {
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String getHelpForm() {
		return "contact";
	}
	@RequestMapping(value="/contact", method=RequestMethod.POST)
	public String contact(@ModelAttribute(name="contactForm") ContactForm contactForm, Model model) {
		String firstName = contactForm.getFirstName();
		String lastName = contactForm.getLastName();
		String email = contactForm.getEmail();
		String city = contactForm.getCity();
		String state = contactForm.getState();
		if(firstName != null & lastName != null & email != null & city != null & state != null ) {
		return "Thank you that you share your opinion with us";
	}
		return "You don't complete all fields!";
	}

}
