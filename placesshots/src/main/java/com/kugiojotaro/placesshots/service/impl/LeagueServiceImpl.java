package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.entity.League;
import com.kugiojotaro.placesshots.mapper.LeagueMapper;
import com.kugiojotaro.placesshots.repository.LeagueRepository;
import com.kugiojotaro.placesshots.service.LeagueService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private LeagueMapper leagueMapper;
	
	@Override
	public Boolean create(LeagueDto leagueDto) {
		log.info(" create");
		
		try {
			League league = new League();
			league.setTitle(leagueDto.getTitle());
			
			leagueRepository.save(league);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return true;
	}

	@Override
	public Boolean update(LeagueDto leagueDto) {
		log.info(" update");
		
		return true;
	}

	@Override
	public Boolean delete(Short id) {
		log.info(" delete");
		
		return true;
	}

	@Override
	public List<LeagueDto> findAll() {
		log.info(" findAll");
		
		List<LeagueDto> result = new ArrayList<LeagueDto>();
		
		try {
			List<League> listLeague = leagueRepository.findAll();
			result = leagueMapper.toDtoBean(listLeague);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return result;
	}
	
}