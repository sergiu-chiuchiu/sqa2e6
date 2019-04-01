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
import com.sqa.onlinepizzastore.services.AppProductService;
import com.sqa.onlinepizzastore.services.IngredientService;

@Controller
public class AppProductController {

	 @Autowired
	 AppProductService appProductService;
	 
	 @Autowired
	 IngredientService ingredientService;

 
//VIEW PIZZAS-----------------------------------------
	 
	 	@RequestMapping(value = "/menu/pizza", method = RequestMethod.GET)
	 	public ModelAndView viewcustoms() {	 		
	 		ModelAndView mav = new ModelAndView("Pizza"); 
	 		mav.addObject("pizzas", appProductService.findAll());
	 		return mav;
	 	}
	 	@ModelAttribute("pizzas")
	 	public List<AppProduct> pizzas() {
	     return appProductService.findAll();
	 	}
		 
	 	@ModelAttribute("ingredients")
		 public List<Ingredient> ingredients() {
		   return ingredientService.findAll();
		 	}
}
