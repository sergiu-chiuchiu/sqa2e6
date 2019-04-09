package com.sqa.onlinepizzastore.controllers;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.onlinepizzastore.dto.RevenueReportDto;
import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.services.IngredientService;
import com.sqa.onlinepizzastore.services.ReportService;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private ReportService reportService;

	@GetMapping(value = "ordersReport")
	public String getOrdersReport(Model model) {
		return "RMostOrderedPizza";
	}

	@GetMapping(value = "revenueReport")
	public String getRevenueReport(Model model) {
		List<RevenueReportDto> revenueReportDto = reportService.getRevenueReportData();

		final DecimalFormat df2 = new DecimalFormat(".##");
		
		Double totalRevenue = 0.0;
		for (RevenueReportDto rr : revenueReportDto) {
			totalRevenue += rr.getOrderTotal();
		}
		totalRevenue = Double.parseDouble(df2.format(totalRevenue));

		Double totalCost = totalRevenue * 0.73;
		totalCost = Double.parseDouble(df2.format(totalCost));

		// calc net income
		Double netIncome = totalRevenue - totalCost;
		netIncome = Double.parseDouble(df2.format(netIncome));
		System.out.println("netincome " + netIncome);
		
		
		
		
		model.addAttribute("report", revenueReportDto);
		model.addAttribute("totalRevenue", totalRevenue);
		model.addAttribute("totalCost", totalCost);
		model.addAttribute("netIncome", netIncome);

		return "RTotalrevenueandCosts";
	}

	@GetMapping(value = "quantityReport")
	public String getTotalQuantityPerIngredientReport(Model model) {
		List<Ingredient> ingredients = ingredientService.findAllSortedAsc("fds");
		model.addAttribute("ingredients", ingredients);
		return "RTotalQuantity";
	}

}
