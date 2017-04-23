package com.kugiojotaro.placesshots.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kugiojotaro.placesshots.entity.Predict;
import com.kugiojotaro.placesshots.entity.qdsl.QPredict;
import com.kugiojotaro.placesshots.repository.PredictCustomRepository;
import com.mysema.query.jpa.impl.JPAQuery;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class PredictCustomRepositoryImpl implements PredictCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Predict> findByUserIdAndWeek(Integer userId, Short week) {
		log.info(" findByUserIdAndWeek: " + userId + ", " + week);
		List<Predict> result = new JPAQuery(entityManager).from(QPredict.predict).where(QPredict.predict.fixture().week.eq(week).and(QPredict.predict.userId.eq(userId))).list(QPredict.predict);
		log.info(" result size: " + result.size());
		
	    return result;
	}

}