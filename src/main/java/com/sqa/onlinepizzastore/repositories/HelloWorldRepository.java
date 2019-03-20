package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.HelloWorld;


@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorld, Long> {
	
}
