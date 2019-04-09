package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppCartDetail;
import com.sqa.onlinepizzastore.entitites.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	
	
}
