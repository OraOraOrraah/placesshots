package com.kugiojotaro.placesshots.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.UserItem;

public interface UserItemDao extends JpaRepository<UserItem, Long> {
	
}