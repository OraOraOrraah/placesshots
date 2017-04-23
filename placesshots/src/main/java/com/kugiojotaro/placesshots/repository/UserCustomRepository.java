package com.kugiojotaro.placesshots.repository;

import org.springframework.data.repository.query.Param;

import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.entity.UserConnection;

public interface UserCustomRepository {

	public User findByProviderUserId(@Param("providerUserId") String providerUserId);

	public int joinUserConnection(Integer userId, UserConnection userConnection);
	
}