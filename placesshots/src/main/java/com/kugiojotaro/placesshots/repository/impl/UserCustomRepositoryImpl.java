package com.kugiojotaro.placesshots.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kugiojotaro.placesshots.entity.User;
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
		JPAQuery query = new JPAQuery(entityManager);
	    User user = query.from(QUser.user).where(QUser.user.userConnection().id().providerUserId.eq(providerUserId)).uniqueResult(QUser.user);
	    return user;
	}

}