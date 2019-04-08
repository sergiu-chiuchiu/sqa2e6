package com.sqa.onlinepizzastore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppCartDetail;
import com.sqa.onlinepizzastore.repositories.AppCartDetailRepository;
import com.sqa.onlinepizzastore.repositories.AppCartRepository;
import com.sqa.onlinepizzastore.services.AppCartDetailService;

@Service
public class AppCartDetailServiceImpl implements AppCartDetailService {

	@Autowired
	AppCartDetailRepository appCartDetailRepository;
	
	@Override
	public AppCartDetail saveAppCartDetail(AppCartDetail appCartDetail) {
		return appCartDetailRepository.save(appCartDetail);
	}

	@Override
	public List<AppCartDetail> getAppCartDetailByCartNo(Long cartNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppCartDetail getAppCartByCartNo(Long cartNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
