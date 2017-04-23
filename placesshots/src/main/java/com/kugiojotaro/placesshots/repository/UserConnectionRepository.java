package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.UserConnection;
import com.kugiojotaro.placesshots.entity.UserConnectionId;

public interface UserConnectionRepository extends JpaRepository<UserConnection, UserConnectionId> {
	
}