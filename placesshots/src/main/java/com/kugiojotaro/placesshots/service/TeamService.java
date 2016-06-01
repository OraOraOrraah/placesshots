package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.TeamDto;

public interface TeamService {

	@Transactional
	public Boolean create(TeamDto teamDto);

	@Transactional
	public Boolean update(TeamDto teamDto);

	@Transactional
	public TeamDto selectById(Integer id);
	
	@Transactional
	public Boolean delete(Integer id);

	List<TeamDto> findByLeague(Short leagueId);

}