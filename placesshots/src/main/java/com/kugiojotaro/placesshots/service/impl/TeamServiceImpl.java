package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dao.LeagueDao;
import com.kugiojotaro.placesshots.dao.TeamDao;
import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.mapper.TeamMapper;
import com.kugiojotaro.placesshots.service.TeamService;
import com.kugiojotaro.placesshots.util.Helper;

@Service
public class TeamServiceImpl implements TeamService {

	private static final Logger LOGGER = Logger.getLogger(TeamServiceImpl.class);

	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private LeagueDao leagueDao;
	
	@Autowired
	private TeamMapper teamMapper;
	
	@Override
	public Boolean create(TeamDto teamDto) {
		LOGGER.debug(" create");
		
		try {
			Team team = teamMapper.toPersistenceBean(teamDto);
			team.setLeague(Helper.string2Short(teamDto.getLeagueId()));
			teamDao.save(team);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(TeamDto teamDto) {
		LOGGER.debug(" update");
		
		try {
			Team team = teamDao.findOne(Helper.string2Integer(teamDto.getId()));
			team = teamMapper.toPersistenceBean(teamDto);
			team.setLeague(Helper.string2Short(teamDto.getLeagueId()));
			teamDao.save(team);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public TeamDto selectById(Integer id) {
		LOGGER.debug(" selectById");
		
		TeamDto result = null;
		
		try {
			result = teamMapper.toDtoBean(teamDao.findOne(id));
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public Boolean delete(Integer id) {
		LOGGER.debug(" delete");
		
		return true;
	}

	@Override
	public List<TeamDto> findByLeague(Short leagueId) {
		LOGGER.debug(" findByLeague");
		
		List<TeamDto> result = new ArrayList<TeamDto>();
		
		try {
			List<Team> listTeam = teamDao.findByLeague(leagueId);
			result = teamMapper.toDtoBean(listTeam);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
}