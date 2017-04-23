
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.PredictChampionDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDto;
import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.dto.PredictResultDto;
import com.kugiojotaro.placesshots.dto.UserPointDto;
import com.kugiojotaro.placesshots.dto.UserPredictPerformanceDto;

public interface PredictService {

	@Transactional
	public Boolean create(PredictDto predictDto);
	
	@Transactional
	public Boolean predict(Integer userId, Short week, List<PredictDto> listPredictDto);

	/*
	@Transactional
	public Boolean update(PredictDto predictDto);

	@Transactional
	public Boolean delete(Long id);
	*/

	List<PredictDto> findByUserId(Integer userId);
	
	List<PredictDto> findByWeek(Short week);
	
	List<PredictDto> findByUserIdAndWeek(Integer userId, Short week);
	
	List<PredictResultDto> weeklyResult(Short week);
	
	List<PredictResultDto> result(Integer userId);
	
	List<UserPointDto> standing(String round);
	
//	@Transactional
//	public Boolean updatePoint(Short week);

	@Transactional
	public Boolean predictChampion(PredictChampionDto predictChampionDto);
	
	@Transactional
	public PredictChampionDto findPredictChampionByUserId(Integer userId);
	
	List<PredictChampionDisplayDto> resultPredictChampion();
	
	List<UserPredictPerformanceDto> performance(Integer userId);
	
}