package com.sqa.onlinepizzastore.dto;

import java.util.ArrayList;
import java.util.List;

public class RevenueReportDto {

	private Double orderTotal;

	private Double orderCost;

	private Double totalRevenue;

	private Double totalCost;

	private Double netIncome;


	
	
	//	public List<Double> getOrderTotal() {
//		return orderTotal;
//	}
//
//	public void setOrderTotal(List<Double> orderTotal) {
//		this.orderTotal = orderTotal;
//	}
//
//	public void addOrderTotal(Double orderTotal) {
//		this.orderTotal.add(orderTotal);
//	}
//	
//	public List<Double> getOrderCost() {
//		return orderCost;
//	}
//
//	public void setOrderCost(List<Double> orderCost) {
//		this.orderCost = orderCost;
//	}
//
//	public void addOrderCost(Double orderCost) {
//		this.orderCost.add(orderCost);
//	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

}
