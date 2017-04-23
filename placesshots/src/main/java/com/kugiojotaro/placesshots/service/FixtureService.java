
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.FixtureDataTablesDto;
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
	
	@Transactional(readOnly = true)
	public FixtureDto selectById(Long id);

	@Transactional(readOnly = true)
	public List<FixtureDto> findByLeagueAndWeek(Short leagueId, Short week);
	
	@Transactional(readOnly = true)
	public List<FixtureDto> findByLeague(Short leagueId);
	
	@Transactional(readOnly = true)
	public List<FixtureDto> findByRound(String round);
	
	@Transactional(readOnly = true)
	public DataTablesOutput<FixtureDataTablesDto> getFixture(DataTablesInput input);

}