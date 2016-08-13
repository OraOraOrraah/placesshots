package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.mapper.BeanMapper;
import com.kugiojotaro.placesshots.repository.TeamRepository;
import com.kugiojotaro.placesshots.service.TeamService;
import com.kugiojotaro.placesshots.util.Helper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private BeanMapper<TeamDto, Team> beanMapper;
	
	@Override
	public Boolean create(TeamDto teamDto) {
		log.info(" create");
		
		try {
			Team team = beanMapper.toPersistenceBean(teamDto);
			team.setLeague(Helper.string2Short(teamDto.getLeagueId()));
			teamRepository.save(team);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(TeamDto teamDto) {
		log.debug(" update");
		
		try {
			Team team = teamRepository.findOne(Helper.string2Integer(teamDto.getId()));
			team = beanMapper.toPersistenceBean(teamDto);
			team.setLeague(Helper.string2Short(teamDto.getLeagueId()));
			teamRepository.save(team);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public TeamDto selectById(Integer id) {
		log.debug(" selectById");
		
		TeamDto result = null;
		
		try {
			result = beanMapper.toDtoBean(teamRepository.findOne(id));
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
	@Override
	public Boolean delete(Integer id) {
		log.debug(" delete");
		
		return true;
	}

	@Override
	public List<TeamDto> findByLeague(Short leagueId) {
		log.debug(" findByLeague");
		
		List<TeamDto> result = new ArrayList<TeamDto>();
		
		try {
			List<Team> listTeam = teamRepository.findByLeague(leagueId);
			result = beanMapper.toDtoBean(listTeam);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
}