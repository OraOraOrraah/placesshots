
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.PredictChampionDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDto;
import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.dto.PredictResultDto;
import com.kugiojotaro.placesshots.dto.UserPointDto;

public interface PredictService {

	@Transactional
	public Boolean create(PredictDto predictDto);
	
	@Transactional
	public Boolean predict(List<PredictDto> listPredictDto);

	/*
	@Transactional
	public Boolean update(PredictDto predictDto);

	@Transactional
	public Boolean delete(Long id);
	*/

	List<PredictDto> findByUser(String username);
	
	List<PredictDto> findByWeek(Short week);
	
	List<PredictDto> findByUserAndWeek(String username, Short week);
	
	List<PredictResultDto> weeklyResult(Short week);
	
	List<PredictResultDto> result(String username);
	
	List<UserPointDto> standing(Short week);
	
	@Transactional
	public Boolean updatePoint(Short week);

	@Transactional
	public Boolean predictChampion(PredictChampionDto predictChampionDto);
	
	@Transactional
	public PredictChampionDto findPredictChampionByUser(String username);
	
	List<PredictChampionDisplayDto> resultPredictChampion();
	
}