package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.FixtureDataTablesDto;
import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.mapper.FixtureDataTablesMapper;
import com.kugiojotaro.placesshots.mapper.FixtureMapper;
import com.kugiojotaro.placesshots.predicate.FixturePredicate;
import com.kugiojotaro.placesshots.repository.FixtureQDataTablesRepository;
import com.kugiojotaro.placesshots.repository.FixtureRepository;
import com.kugiojotaro.placesshots.repository.TeamRepository;
import com.kugiojotaro.placesshots.service.FixtureService;
import com.kugiojotaro.placesshots.util.Helper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class FixtureServiceImpl implements FixtureService {
	
	@Autowired
	private FixtureRepository fixtureRepository;
	
	@Autowired
	private FixtureQDataTablesRepository fixtureQDataTablesRepository;
	
	@Autowired
	private TeamRepository teamRtepository;
	
	@Autowired
	private FixtureMapper fixtureMapper;
	
	@Autowired
	private FixtureDataTablesMapper fixtureDataTablesMapper;
	
	@Override
	public Boolean create(FixtureDto fixtureDto) {
		log.debug(" create");
		
		try {
			Fixture fixture = fixtureMapper.toPersistenceBean(fixtureDto);
			fixture.setLeague(Helper.string2Short(fixtureDto.getLeagueId()));
			fixture.setHome(teamRtepository.findOne(Helper.string2Integer(fixtureDto.getHomeId())));
			fixture.setAway(teamRtepository.findOne(Helper.string2Integer(fixtureDto.getAwayId())));
			fixture.setFixtureDate(Helper.string2Date(fixtureDto.getFixtureDate()));
			fixture.setCreateDate(new Date());
			
			fixtureRepository.save(fixture);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(FixtureDto fixtureDto) {
		log.debug(" update");
		
		try {
			Fixture fixture = fixtureRepository.findOne(Helper.string2Long(fixtureDto.getId()));
			fixture.setLeague(Helper.string2Short(fixtureDto.getLeagueId()));
			fixture.setHome(teamRtepository.findOne(Helper.string2Integer(fixtureDto.getHomeId())));
			fixture.setAway(teamRtepository.findOne(Helper.string2Integer(fixtureDto.getAwayId())));
			fixture.setFixtureDate(Helper.string2Date(fixtureDto.getFixtureDate()));
			fixture.setUpdateDate(new Date());
			
			fixtureRepository.save(fixture);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}
	
	@Override
	public Boolean updateScore(FixtureDto fixtureDto) {
		log.debug(" updateScore");
		
		try {
			Fixture fixture = fixtureRepository.findOne(Helper.string2Long(fixtureDto.getId()));
			fixture.setHomeScore(Helper.string2Short(fixtureDto.getHomeScore()));
			fixture.setAwayScore(Helper.string2Short(fixtureDto.getAwayScore()));
			fixture.setHomeExtraTimeScore(fixtureDto.getHomeExtraTimeScore() == "" ? null : Helper.string2Short(fixtureDto.getHomeExtraTimeScore()));
			fixture.setAwayExtraTimeScore(fixtureDto.getAwayExtraTimeScore() == "" ? null : Helper.string2Short(fixtureDto.getAwayExtraTimeScore()));
			fixture.setHomePenaltyScore(fixtureDto.getHomePenaltyScore() == "" ? null : Helper.string2Short(fixtureDto.getHomePenaltyScore()));
			fixture.setAwayPenaltyScore(fixtureDto.getAwayPenaltyScore() == "" ? null : Helper.string2Short(fixtureDto.getAwayPenaltyScore()));
			fixture.setUpdateDate(new Date());
			
			fixtureRepository.save(fixture);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean delete(Short id) {
		log.debug(" delete");
		
		return true;
	}

	@Override
	public FixtureDto selectById(Long id) {
		log.debug(" selectById");
		
		FixtureDto result = new FixtureDto();
		
		try {
			result = fixtureMapper.toDtoBean(fixtureRepository.findOne(id));
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<FixtureDto> findByLeagueAndWeek(Short leagueId, Short week) {
		log.debug(" findByLeagueAndWeek");
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureRepository.findByLeagueAndWeek(leagueId, week);
			//result = fixtureMapper.toDtoBean(listFixture);
			
			for (Fixture fixture : listFixture) {
				FixtureDto fixtureDto = fixtureMapper.toDtoBean(fixture);
				fixtureDto.setFixtureDate(Helper.formatFixtureDate(fixture.getFixtureDate()));
				fixtureDto.setHomeId(fixture.getHome().getId() + "");
				fixtureDto.setHomeTitle(fixture.getHome().getTitle() + "");
				fixtureDto.setHomeShortTitle(fixture.getHome().getShortTitle() + "");
				fixtureDto.setAwayId(fixture.getAway().getId() + "");
				fixtureDto.setAwayTitle(fixture.getAway().getTitle() + "");
				fixtureDto.setAwayShortTitle(fixture.getAway().getShortTitle() + "");
				
				result.add(fixtureDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<FixtureDto> findByLeague(Short leagueId) {
		log.debug(" findByLeagueAndWeek");
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureRepository.findByLeague(leagueId);
			//result = fixtureMapper.toDtoBean(listFixture);
			
			for (Fixture fixture : listFixture) {
				FixtureDto fixtureDto = fixtureMapper.toDtoBean(fixture);
				fixtureDto.setHomeId(fixture.getHome().getId() + "");
				fixtureDto.setHomeTitle(fixture.getHome().getTitle() + "");
				fixtureDto.setHomeShortTitle(fixture.getHome().getShortTitle() + "");
				fixtureDto.setAwayId(fixture.getAway().getId() + "");
				fixtureDto.setAwayTitle(fixture.getAway().getTitle() + "");
				fixtureDto.setAwayShortTitle(fixture.getAway().getShortTitle() + "");
				
				result.add(fixtureDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<FixtureDto> findByRound(String round) {
		log.debug(" findByRound: " + round);
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureRepository.findByRound(round);
			//result = fixtureMapper.toDtoBean(listFixture);
			
			for (Fixture fixture : listFixture) {
				FixtureDto fixtureDto = fixtureMapper.toDtoBean(fixture);
				fixtureDto.setHomeId(fixture.getHome().getId() + "");
				fixtureDto.setHomeTitle(fixture.getHome().getTitle() + "");
				fixtureDto.setHomeShortTitle(fixture.getHome().getShortTitle() + "");
				fixtureDto.setAwayId(fixture.getAway().getId() + "");
				fixtureDto.setAwayTitle(fixture.getAway().getTitle() + "");
				fixtureDto.setAwayShortTitle(fixture.getAway().getShortTitle() + "");
				
				result.add(fixtureDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public DataTablesOutput<FixtureDataTablesDto> getFixture(DataTablesInput input) {
		log.info(" getFixture: " + input);
		
		DataTablesOutput<Fixture> dtoFixture = fixtureQDataTablesRepository.findAll(input, FixturePredicate.inquiry(input));
		DataTablesOutput<FixtureDataTablesDto> result = fixtureDataTablesMapper.toDtoBean(dtoFixture);
		result.setData(new ArrayList<FixtureDataTablesDto>());
		for (Fixture fixture : dtoFixture.getData()) {
			FixtureDataTablesDto fixtureDataTablesDto = fixtureDataTablesMapper.toDtoBean(fixture);
			fixtureDataTablesDto.setHomeTitle(fixture.getHome().getTitle());
			fixtureDataTablesDto.setAwayTitle(fixture.getAway().getTitle());
			fixtureDataTablesDto.setHomeShortTitle(fixture.getHome().getShortTitle());
			fixtureDataTablesDto.setAwayShortTitle(fixture.getAway().getShortTitle());
			result.getData().add(fixtureDataTablesDto);
		}
		return result;
	}
	
}