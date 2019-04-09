package com.sqa.onlinepizzastore.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppCart;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.CartItem;
import com.sqa.onlinepizzastore.repositories.AppCartRepository;
import com.sqa.onlinepizzastore.services.AppCartService;

@Service
public class AppCartServiceImpl implements AppCartService {

	@Autowired
	AppCartRepository appCartRepository;
	
	@Override
	public AppCart saveAppCart(AppCart appCart) {
		return appCartRepository.save(appCart);
	}

	@Override
	public List<AppCart> getAppCartByAppUserEmail(String email) {
		return appCartRepository.getAppCartByAppUserEmail(email);
	}

	@Override
	public AppCart getAppCartByCartNo(Long cartNo) {
		return appCartRepository.getAppCartByCartNo(cartNo);
	}

	@Override
	public AppCart getAppCartByActive(char c) {
		return appCartRepository.getAppCartByActive(c);
	}

	@Override
	public Set<CartItem> getAppCartGetCartItems(AppCart appCart) {
		return appCartRepository.getAppCartByCartNo(appCart.getCartNo()).getCartItems();
	}

	@Override
	public AppCart updateAppCart(AppCart appCart) {
		
		return appCartRepository.save(appCart);
	}


}
