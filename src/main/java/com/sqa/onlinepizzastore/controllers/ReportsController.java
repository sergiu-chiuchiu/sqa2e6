package com.sqa.onlinepizzastore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.services.IngredientService;



@Controller
@RequestMapping(value = "/reports")
public class ReportsController {
	@Autowired
	private IngredientService ingredientService;
	
	
	@GetMapping(value = "ordersReport")
	public String getOrdersReport(Model model) {
		return "RMostOrderedPizza";
	}
	
	@GetMapping(value = "revenueReport")
	public String getRevenueReport(Model model) {
		return "RTotalrevenueandCosts";
	}
	
	@GetMapping(value = "quantityReport")
	public String getTotalQuantityPerIngredientReport(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		return "RTotalQuantity";
	}
	
}
