package com.sqa.onlinepizzastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqa.onlinepizzastore.entitites.AppSubscribe;

@Repository
public interface AppSubscribeRepository extends JpaRepository<AppSubscribe, Long> {

}
