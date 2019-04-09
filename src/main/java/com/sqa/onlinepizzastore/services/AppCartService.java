package com.sqa.onlinepizzastore.services;

import java.util.List;
import java.util.Set;

import com.sqa.onlinepizzastore.entitites.AppCart;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.CartItem;


public interface AppCartService {

	AppCart saveAppCart(AppCart appCart);
	
	AppCart updateAppCart(AppCart appCart);


	List<AppCart> getAppCartByAppUserEmail(String email);

	AppCart getAppCartByCartNo(Long cartNo);
	
	AppCart getAppCartByActive(char c);
	
	Set<CartItem> getAppCartGetCartItems(AppCart appCart);
	
}
