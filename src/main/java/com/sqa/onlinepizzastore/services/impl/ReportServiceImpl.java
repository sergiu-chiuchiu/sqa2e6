package com.sqa.onlinepizzastore.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.dto.RevenueReportDto;
import com.sqa.onlinepizzastore.entitites.AppOrder;
import com.sqa.onlinepizzastore.repositories.AppOrderRepository;
import com.sqa.onlinepizzastore.services.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private AppOrderRepository appOrderRepository;
	
	@Override
	public List<RevenueReportDto> getRevenueReportData() {
		List<AppOrder> appOrders = appOrderRepository.findAll();
		List<RevenueReportDto> revenueReportsDto = new ArrayList<>();
		
		final DecimalFormat df2 = new DecimalFormat(".##");
		
		for(AppOrder ao : appOrders) {
			RevenueReportDto revenueReportDto = new RevenueReportDto();
			revenueReportDto.setOrderTotal(Double.parseDouble(df2.format(ao.getTotalAmt())));
			revenueReportDto.setOrderCost(Double.parseDouble(df2.format(ao.getTotalAmt() * 0.73)));
			revenueReportsDto.add(revenueReportDto);
		}

		return revenueReportsDto;	
	}

}
