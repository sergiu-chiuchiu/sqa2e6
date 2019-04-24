package com.sqa.onlinepizzastore.controllers;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.services.IngredientService;


@Controller
public class IngredientController {
 
 @Autowired
 IngredientService ingredientService;
 

// VIEW INGREDIENTS-----------------------------------------
 
 	@RequestMapping(value = "/menu/ingredients", method = RequestMethod.GET)
 	public ModelAndView messages() {
 		ModelAndView mav = new ModelAndView("ingredients"); //aici trebuie adaugat o pagina pentru a vizualiza ingredientele
 		mav.addObject("ingredients", ingredientService.findAll());
 		return mav;
 	}
 	@ModelAttribute("ingredients")
 	public List<Ingredient> ingredients() {
     return ingredientService.findAll();
 	}
 
//---------------------DELETE
 	@RequestMapping(value="/deleteIngredient/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id) {
		Ingredient ingredient=ingredientService.findOne(id);		
		ingredientService.delete(ingredient);
		return new ModelAndView("redirect:/menu/ingredients");
	}
//---------------------- UPDATE	

	
	@RequestMapping(value="/menu/ingredients/editIngredient/{id}",method=RequestMethod.GET)
	public String edit (@PathVariable Integer id, Model model) {		
		Ingredient ingredient=ingredientService.findOne(id);
		model.addAttribute("ingredient",ingredient);
		return "editIngredient";
	}
	
	@RequestMapping(value="/menu/ingredients/editIngredient",method=RequestMethod.POST)
	public String editsave(@Valid @ModelAttribute("ingredient") Ingredient i, BindingResult bindingResult, Model model) {		
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			return "editIngredient";
		}
		Ingredient ingredient=ingredientService.findOne(i.getIng_id());		
		ingredient.setIng_name(i.getIng_name());
		ingredient.setIng_type(i.getIng_type());
		ingredient.setIng_allergen(i.getIng_allergen());
		ingredient.setIng_weight(i.getIng_weight());
		ingredient.setIng_price(i.getIng_price());
		ingredient.setIng_quantity(i.getIng_quantity());
		ingredient.setEnergy(i.getEnergy());
		ingredient.setFat(i.getFat());
		ingredient.setCarbohydrate(i.getCarbohydrate());
		ingredient.setFibres(i.getFibres());
		ingredient.setProteine(i.getProteine());
		ingredient.setSalt(i.getSalt());
		
		ingredientService.save(ingredient);
		return new String("redirect:/menu/ingredients");
	}		
//-------------------------CREATE
	
	@RequestMapping(value="/menu/ingredients/addIngredient",method=RequestMethod.GET)
	public String addIngredient(Model model) {		
		model.addAttribute("i", new Ingredient());
		return "addIngredient";
	}
	
	@RequestMapping(value="/menu/ingredients/addIngredient", method=RequestMethod.POST)
	public String addNewIngredient(@Valid @ModelAttribute("i") Ingredient i, BindingResult bindingResult, Model model) {		
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			return "addIngredient";
		}
		model.addAttribute("Ing_id", i.getIng_id());
		model.addAttribute("Ing_name", i.getIng_name());
		model.addAttribute("Ing_type", i.getIng_type());
		model.addAttribute("Ing_allergen", i.getIng_allergen());
		model.addAttribute("Ing_price", i.getIng_price());
		model.addAttribute("Ing_quantity", i.getIng_quantity());
		model.addAttribute("Energy", i.getEnergy());
		model.addAttribute("Fat", i.getFat());
		model.addAttribute("Carbohydrate", i.getCarbohydrate());
		model.addAttribute("Fibres", i.getFibres());
		model.addAttribute("Proteine", i.getProteine());
		model.addAttribute("Salt", i.getSalt());			
		ingredientService.save(i);
		return new String("redirect:/menu/ingredients");
}
}