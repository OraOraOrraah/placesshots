package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dao.LeagueDao;
import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.entity.League;
import com.kugiojotaro.placesshots.mapper.LeagueMapper;
import com.kugiojotaro.placesshots.service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService {

	private static final Logger LOGGER = Logger.getLogger(LeagueServiceImpl.class);

	@Autowired
	private LeagueDao leagueDao;
	
	@Autowired
	private LeagueMapper leagueMapper;
	
	@Override
	public Boolean create(LeagueDto leagueDto) {
		LOGGER.info(" create");
		
		try {
			League league = new League();
			league.setTitle(leagueDto.getTitle());
			
			leagueDao.save(league);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(LeagueDto leagueDto) {
		LOGGER.info(" update");
		
		return true;
	}

	@Override
	public Boolean delete(Short id) {
		LOGGER.info(" delete");
		
		return true;
	}

	@Override
	public List<LeagueDto> findAll() {
		LOGGER.info(" findAll");
		
		List<LeagueDto> result = new ArrayList<LeagueDto>();
		
		try {
			List<League> listLeague = leagueDao.findAll();
			result = leagueMapper.toDtoBean(listLeague);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}
	
}