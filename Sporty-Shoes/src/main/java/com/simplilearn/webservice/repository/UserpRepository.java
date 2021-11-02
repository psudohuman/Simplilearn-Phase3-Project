package com.simplilearn.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.webservice.entity.UserPurchase;

public interface UserpRepository extends JpaRepository<UserPurchase, Long> {
	
}
