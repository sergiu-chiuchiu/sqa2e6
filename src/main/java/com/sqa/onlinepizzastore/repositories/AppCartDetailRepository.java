package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppCartDetail;

@Repository
public interface AppCartDetailRepository extends JpaRepository<AppCartDetail, Long> {

	
}
