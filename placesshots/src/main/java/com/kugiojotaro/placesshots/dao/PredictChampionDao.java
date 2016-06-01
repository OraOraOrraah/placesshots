package com.kugiojotaro.placesshots.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kugiojotaro.placesshots.entity.PredictChampion;

public interface PredictChampionDao extends JpaRepository<PredictChampion, Long>, JpaSpecificationExecutor<PredictChampion> {
	
	public PredictChampion findByUser(String username);
	
}