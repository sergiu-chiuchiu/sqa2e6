package com.sqa.onlinepizzastore.services;

import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.CartItem;

public interface CartItemService {

	CartItem saveCartItem(CartItem cartItem);
	
	CartItem updateCartItem(CartItem cartItem);
	
	void deleteCartItem(CartItem cartItemToDelete);
}
