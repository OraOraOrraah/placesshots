package com.kugiojotaro.placesshots.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.Predict;

public interface PredictRepository extends JpaRepository<Predict, Long> {
	
	public List<Predict> findByUserId(Integer userId);
	
//	public List<Predict> findByWeek(Short week);
//	
//	public List<Predict> findByWeekOrderByUserIdAsc(Short week);
	
//	public List<Predict> findByUserIdAndWeek(Integer userId, Short week);
	
}