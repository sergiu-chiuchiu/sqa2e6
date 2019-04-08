package com.sqa.onlinepizzastore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.repositories.AppProductRepository;
import com.sqa.onlinepizzastore.services.AppProductService;
import com.sqa.onlinepizzastore.services.IngredientService;

@Controller
public class AppProductController {

	 @Autowired
	 AppProductService appProductService;
	 
	 @Autowired
	 IngredientService ingredientService;
	 
	 @Autowired
	 AppProductRepository appProductRepository;

 
//VIEW PIZZAS-----------------------------------------
	 
	 	@RequestMapping(value = "/menu/pizza", method = RequestMethod.GET)
	 	public ModelAndView viewCustoms() {	 		
	 		ModelAndView mav = new ModelAndView("Pizza"); 
	 		mav.addObject("pizzas", appProductRepository.findPizzas());
	 		return mav;
	 	}
	 	@ModelAttribute("pizzas")
	 	public List<AppProduct> pizzas() {
	     return appProductRepository.findPizzas();
	 	}
		 
	 	@ModelAttribute("ingredients")
		 public List<Ingredient> ingredients() {
		   return ingredientService.findAll();
		}
	 	
//VIEW DIPS-----------------------------------------	 	
		@RequestMapping(value = "/menu/dips", method = RequestMethod.GET)
	 	public ModelAndView viewDips() {	 		
	 		ModelAndView mav = new ModelAndView("Dips"); 
	 		mav.addObject("pizzas", appProductRepository.findDips());
	 		return mav;
	 	}
	 	@ModelAttribute("dips")
	 	public List<AppProduct> dips() {
	     return appProductRepository.findDips();
	 	}
//VIEW Drinks-----------------------------------------	 	
	 	@RequestMapping(value = "/menu/drinks", method = RequestMethod.GET)
	 	public ModelAndView viewDrinks() {	 		
	 		ModelAndView mav = new ModelAndView("Drinks"); 
	 		mav.addObject("drinks", appProductRepository.findDrinks());
	 		return mav;
	 	}
	 	@ModelAttribute("drinks")
	 	public List<AppProduct> drinks() {
	     return appProductRepository.findDrinks();
	 	}
//VIEW SODAS-----------------------------------------	 	

	 	@RequestMapping(value = "/menu/drinks/soda", method = RequestMethod.GET)
	 	public ModelAndView viewSodas() {	 		
	 		ModelAndView mav = new ModelAndView("Soda"); 
	 		mav.addObject("sodas", appProductRepository.findSodas());
	 		return mav;
	 	}
	 	@ModelAttribute("sodas")
	 	public List<AppProduct> sodas() {
	     return appProductRepository.findSodas();
	 	}
//VIEW COCKTAILS-----------------------------------------	 	
	 	
	 	@RequestMapping(value = "/menu/drinks/cocktail", method = RequestMethod.GET)
	 	public ModelAndView viewCocktails() {	 		
	 		ModelAndView mav = new ModelAndView("Cocktail"); 
	 		mav.addObject("cocktails", appProductRepository.findCocktails());
	 		return mav;
	 	}
	 	@ModelAttribute("cocktails")
	 	public List<AppProduct> cocktails() {
	     return appProductRepository.findCocktails();
	 	}
}
