package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.services.AppRoleService;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.WebUtils;

@Controller
@RequestMapping()
public class NavigationControllers {
	 
//	@GetMapping(value = "/index")
//	public String getIndex(Model model) {
//		return "index";
//	}
	@GetMapping(value = "/aboutus")
	public String getAboutUsPage(Model model) {
		return "About us";
	}
	
	@GetMapping(value = "/menu")
	public String getMenuPage(Model model) {
		return "Menu";
	}
	
	@GetMapping(value = "/menu/pizza")
	public String getPizzaPage(Model model) {
		return "Pizza";
	}
	
	@GetMapping(value = "/menu/ingredients")
	public String getIngredientsPage(Model model) {
		return "ingredients";
	}
	
	@GetMapping(value = "/menu/dips")
	public String getDipsPage(Model model) {
		return "Dips";
	}
	
	@GetMapping(value = "/menu/drinks")
	public String getDrinksPage(Model model) {
		return "Drinks";
	}
	
	@GetMapping(value = "/menu/drinks/soda")
	public String getSodaPage(Model model) {
		return "Soda";
	}
	
	@GetMapping(value = "/menu/drinks/cocktail")
	public String getCocktailPage(Model model) {
		return "Cocktail";
	}
	
	@GetMapping(value = "/popularchoices")
	public String getPopularChoicesPage(Model model) {
		return "PopularChoices";
	}
	
	@GetMapping(value = "/promotions") 
	public String getPromotionsPage(Model model) {
		return "Promotions";
	}
	
//	@GetMapping(value = "/help")
//	public String getHelpPage(Model model) {
//		return "Help";
//	}
	
//	@GetMapping(value = "/contact")
//	public String getContactPage(Model model) {
//		return "Contact";
//	}
	
	@GetMapping(value = "/shoppingcart")
	public String getShoppingCartPage(Model model) {
		return "shopping-cart";
	}
	
//	@GetMapping(value = "/login")
//	public String getLogInPage(Model model) {
//		return "LogIn";
//	}
	
	@GetMapping(value = "/terms")
	public String getTermsPage(Model model) {
		return "Terms";
	}
	
	@GetMapping(value = "/privacy")
	public String getPrivacyPage(Model model) {
		return "Privacy";
	}
	
	@GetMapping(value = "/menu/pizza/customizepizza")
	public String getCustomizePizzaPage(Model model) {
		return "CustomizePizza";
	}
	
//	@GetMapping(value = "/signup")
//	public String getSignupPage(Model model) {
//		return "SignUp";
//	}
	
	@GetMapping(value = "/faq")
	public String getFAQPage(Model model) {
		return "FAQ";
	}
	
	@GetMapping(value = "/resetpassword")
	public String getResetPasswordPage(Model model) {
		return "ResetPassword";
	}

}
