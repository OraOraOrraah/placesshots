
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.LeagueDto;

public interface LeagueService {

	@Transactional
	public Boolean create(LeagueDto leagueDto);

	@Transactional
	public Boolean update(LeagueDto leagueDto);

	@Transactional
	public Boolean delete(Short id);
	
	List<LeagueDto> findAll();

}