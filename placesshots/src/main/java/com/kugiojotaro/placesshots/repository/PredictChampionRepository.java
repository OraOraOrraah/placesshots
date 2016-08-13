package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.PredictChampion;

public interface PredictChampionRepository extends JpaRepository<PredictChampion, Long> {
	
	public PredictChampion findByUser(String username);
	
}