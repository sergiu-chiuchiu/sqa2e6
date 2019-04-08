package com.sqa.onlinepizzastore.services;

import java.util.List;

import com.sqa.onlinepizzastore.entitites.AppCart;
import com.sqa.onlinepizzastore.entitites.AppCartDetail;

public interface AppCartDetailService {

	AppCartDetail saveAppCartDetail(AppCartDetail appCartDetail);


	List<AppCartDetail> getAppCartDetailByCartNo(Long cartNo);


	AppCartDetail getAppCartByCartNo(Long cartNo);
	
}
