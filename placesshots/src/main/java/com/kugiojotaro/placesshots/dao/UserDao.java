package com.kugiojotaro.placesshots.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.entity.User;

public interface UserDao extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userItems WHERE u.username = (:username)")
	public User findFetchItem(@Param("username") String username);
	
	public User findByUsername(String username);
	
}