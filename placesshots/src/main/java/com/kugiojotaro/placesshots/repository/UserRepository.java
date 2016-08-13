package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
	
}