
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.entity.Fixture;

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
	
	List<FixtureDto> findByRound(String round);
	
	public DataTablesOutput<Fixture> getFixture(DataTablesInput input);

}