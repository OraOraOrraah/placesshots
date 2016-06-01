
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.FixtureDto;

public interface FixtureService {

	@Transactional
	public Boolean create(FixtureDto fixtureDto);

	@Transactional
	public Boolean update(FixtureDto fixtureDto);
	
	@Transactional
	public Boolean updateScore(FixtureDto fixtureDto);

	@Transactional
	public Boolean delete(Short id);
	
	public FixtureDto selectById(Long id);

	List<FixtureDto> findByLeagueAndWeek(Short leagueId, Short week);
	
	List<FixtureDto> findByLeague(Short leagueId);
	
	List<FixtureDto> findByRound(Short round);

}