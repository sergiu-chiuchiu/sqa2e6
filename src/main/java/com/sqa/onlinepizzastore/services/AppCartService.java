package com.sqa.onlinepizzastore.services;

import java.util.List;

import com.sqa.onlinepizzastore.entitites.AppCart;


public interface AppCartService {

	AppCart saveAppCart(AppCart appCart);


	AppCart getAppCartByAppUserEmail(String email);


	AppCart getAppCartByCartNo(Long cartNo);
	
}
