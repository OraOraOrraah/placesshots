package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dao.FixtureDao;
import com.kugiojotaro.placesshots.dao.TeamDao;
import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.mapper.FixtureMapper;
import com.kugiojotaro.placesshots.service.FixtureService;
import com.kugiojotaro.placesshots.util.Helper;

@Service
public class FixtureServiceImpl implements FixtureService {

	private static final Logger LOGGER = Logger.getLogger(FixtureServiceImpl.class);

	@Autowired
	private FixtureDao fixtureDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private FixtureMapper fixtureMapper;
	
	@Override
	public Boolean create(FixtureDto fixtureDto) {
		LOGGER.debug(" create");
		
		try {
			Fixture fixture = fixtureMapper.toPersistenceBean(fixtureDto);
			fixture.setLeague(Helper.string2Short(fixtureDto.getLeagueId()));
			fixture.setHome(teamDao.findOne(Helper.string2Integer(fixtureDto.getHomeId())));
			fixture.setAway(teamDao.findOne(Helper.string2Integer(fixtureDto.getAwayId())));
			fixture.setFixtureDate(Helper.string2Date(fixtureDto.getFixtureDate()));
			fixture.setCreateDate(new Date());
			
			fixtureDao.save(fixture);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(FixtureDto fixtureDto) {
		LOGGER.debug(" update");
		
		try {
			Fixture fixture = fixtureDao.findOne(Helper.string2Long(fixtureDto.getId()));
			fixture.setLeague(Helper.string2Short(fixtureDto.getLeagueId()));
			fixture.setHome(teamDao.findOne(Helper.string2Integer(fixtureDto.getHomeId())));
			fixture.setAway(teamDao.findOne(Helper.string2Integer(fixtureDto.getAwayId())));
			fixture.setFixtureDate(Helper.string2Date(fixtureDto.getFixtureDate()));
			fixture.setUpdateDate(new Date());
			
			fixtureDao.save(fixture);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}
	
	@Override
	public Boolean updateScore(FixtureDto fixtureDto) {
		LOGGER.debug(" updateScore");
		
		try {
			Fixture fixture = fixtureDao.findOne(Helper.string2Long(fixtureDto.getId()));
			fixture.setHomeScore(Helper.string2Short(fixtureDto.getHomeScore()));
			fixture.setAwayScore(Helper.string2Short(fixtureDto.getAwayScore()));
			fixture.setHomeExtraTimeScore(fixtureDto.getHomeExtraTimeScore() == "" ? null : Helper.string2Short(fixtureDto.getHomeExtraTimeScore()));
			fixture.setAwayExtraTimeScore(fixtureDto.getAwayExtraTimeScore() == "" ? null : Helper.string2Short(fixtureDto.getAwayExtraTimeScore()));
			fixture.setHomePenaltyScore(fixtureDto.getHomePenaltyScore() == "" ? null : Helper.string2Short(fixtureDto.getHomePenaltyScore()));
			fixture.setAwayPenaltyScore(fixtureDto.getAwayPenaltyScore() == "" ? null : Helper.string2Short(fixtureDto.getAwayPenaltyScore()));
			fixture.setUpdateBy(fixtureDto.getUpdateBy());
			fixture.setUpdateDate(new Date());
			
			fixtureDao.save(fixture);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean delete(Short id) {
		LOGGER.debug(" delete");
		
		return true;
	}

	@Override
	public FixtureDto selectById(Long id) {
		LOGGER.debug(" selectById");
		
		FixtureDto result = new FixtureDto();
		
		try {
			result = fixtureMapper.toDtoBean(fixtureDao.findOne(id));
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public List<FixtureDto> findByLeagueAndWeek(Short leagueId, Short week) {
		LOGGER.debug(" findByLeagueAndWeek");
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureDao.findByLeagueAndWeek(leagueId, week);
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
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<FixtureDto> findByLeague(Short leagueId) {
		LOGGER.debug(" findByLeagueAndWeek");
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureDao.findByLeague(leagueId);
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
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public List<FixtureDto> findByRound(Short round) {
		LOGGER.debug(" findByRound: " + round);
		
		List<FixtureDto> result = new ArrayList<FixtureDto>();
		
		try {
			List<Fixture> listFixture = fixtureDao.findByRound(round);
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
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
}