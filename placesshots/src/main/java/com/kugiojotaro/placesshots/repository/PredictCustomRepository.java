package com.kugiojotaro.placesshots.repository;

import java.util.List;

import com.kugiojotaro.placesshots.entity.Predict;

public interface PredictCustomRepository {
	
	public List<Predict> findByUserIdAndWeek(Integer userId, Short week);
	
}