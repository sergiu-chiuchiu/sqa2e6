package com.sqa.onlinepizzastore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllers {
	
	
	@GetMapping(value = "/main")
	public String getIngredientsPage(Model model) {
		return "ingredients";
	}
	
	@GetMapping(value = "/main/index")
	public String getindexPage(Model model) {
		return "index";
	}
	
	@GetMapping(value = "/main/403")
	public String get403Page(Model model) {
		return "403";
	}
	
	@GetMapping(value = "/main/404")
	public String get404Page(Model model) {
		return "PageNotFound";
	}
	
	@GetMapping(value = "/main/faq")
	public String getFAQPage(Model model) {
		return "FAQ";
	}
	
	@GetMapping(value = "/main/aboutUs")
	public String getAboutUsPage(Model model) {
		return "About us";
	}
	
	@GetMapping(value = "/main/menu/drinks/cocktail")
	public String getCocktailPage(Model model) {
		return "Cocktail";
	}
	
	@GetMapping(value = "/main/menu/pizza")
	public String getPizzaPage(Model model) {
		return "Pizza";
	}
	
	@GetMapping(value = "/main/menu/pizza/customizePizza")
	public String getCustomizePizzaPage(Model model) {
		return "CustomizePizza";
	}
	
	@GetMapping(value = "/main/menu/dips")
	public String getDipsPage(Model model) {
		return "Dips";
	}
	
	@GetMapping(value = "/main/menu/drinks")
	public String getDrinksPage(Model model) {
		return "Drinks";
	}
	
	@GetMapping(value = "/main/menu/drinks/soda")
	public String getSodasPage(Model model) {
		return "Soda";
	}
	
	@GetMapping(value = "/main/contact")
	public String getContactPage(Model model) {
		return "Contact";
	}

	@GetMapping(value = "/main/help")
	public String getHelpPage(Model model) {
		return "Help";
	}
	
	@GetMapping(value = "/main/login")
	public String getLogInPage(Model model) {
		return "LogIn";
	}
	
	@GetMapping(value = "/main/menu")
	public String getMenuPage(Model model) {
		return "Menu";
	}
	
	@GetMapping(value = "/main/privacy")
	public String getPrivacyPage(Model model) {
		return "Privacy";
	}
	
	@GetMapping(value = "/main/promotions")
	public String getPromotionsPage(Model model) {
		return "Promotions";
	}
	
	@GetMapping(value = "/main/shoppingCart")
	public String getShoppingCartPage(Model model) {
		return "shopping-cart";
	}
	
	@GetMapping(value = "/main/signUp")
	public String getSignUpPage(Model model) {
		return "SignUp";
	}
	
	@GetMapping(value = "/main/terms")
	public String getTermsPage(Model model) {
		return "Terms";
	}
}
