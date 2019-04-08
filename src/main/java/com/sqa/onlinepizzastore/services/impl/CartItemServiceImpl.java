package com.sqa.onlinepizzastore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.CartItem;
import com.sqa.onlinepizzastore.repositories.AppSubscribeRepository;
import com.sqa.onlinepizzastore.repositories.CartItemRepository;
import com.sqa.onlinepizzastore.services.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Override
	public CartItem saveCartItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public void deleteCartItem(CartItem cartItemToDelete) {
		cartItemRepository.delete(cartItemToDelete);
		
	}

}
