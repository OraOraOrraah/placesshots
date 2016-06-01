package com.kugiojotaro.placesshots.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kugiojotaro.placesshots.entity.Predict;

public interface PredictDao extends JpaRepository<Predict, Long>, JpaSpecificationExecutor<Predict> {
	
	public List<Predict> findByUser(String username);
	
	public List<Predict> findByWeek(Short week);
	
	public List<Predict> findByWeekOrderByUserAsc(Short week);
	
	public List<Predict> findByUserAndWeek(String username, Short week);

}