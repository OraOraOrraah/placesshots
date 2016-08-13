package com.kugiojotaro.placesshots.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.Predict;

public interface PredictRepository extends JpaRepository<Predict, Long> {
	
	public List<Predict> findByUser(String username);
	
	public List<Predict> findByWeek(Short week);
	
	public List<Predict> findByWeekOrderByUserAsc(Short week);
	
	public List<Predict> findByUserAndWeek(String username, Short week);
	
}