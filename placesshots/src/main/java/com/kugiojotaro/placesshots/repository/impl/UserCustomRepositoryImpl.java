package com.kugiojotaro.placesshots.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.entity.UserConnection;
import com.kugiojotaro.placesshots.entity.qdsl.QUser;
import com.kugiojotaro.placesshots.repository.UserCustomRepository;
import com.mysema.query.jpa.impl.JPAQuery;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class UserCustomRepositoryImpl implements UserCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User findByProviderUserId(String providerUserId) {
	    User user = new JPAQuery(entityManager).from(QUser.user).where(QUser.user.userConnection().id().providerUserId.eq(providerUserId)).uniqueResult(QUser.user);
	    return user;
	}

	@Override
	public int joinUserConnection(Integer userId, UserConnection userConnection) {
		log.info(" joinUserConnection " + userId + ", " + userConnection.getId().getProviderId());
		return entityManager.createNativeQuery("INSERT INTO user_connect(user_id, userId, providerId, providerUserId) VALUES (" + userId + ", '" + userConnection.getId().getUserId() + "', '" + userConnection.getId().getProviderId() + "', '" + userConnection.getId().getProviderUserId() + "')").executeUpdate();
	}

}