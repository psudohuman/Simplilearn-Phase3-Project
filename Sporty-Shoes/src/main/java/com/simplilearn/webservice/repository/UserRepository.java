package com.simplilearn.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.webservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(String iid);
	
}
