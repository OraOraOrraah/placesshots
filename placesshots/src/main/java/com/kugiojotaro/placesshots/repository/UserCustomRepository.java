package com.kugiojotaro.placesshots.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.entity.User;

public interface UserCustomRepository {

	@Transactional(readOnly = true)
	public User findByProviderUserId(@Param("providerUserId") String providerUserId);
	
}