package com.kugiojotaro.placesshots.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.Team;

public interface TeamDao extends JpaRepository<Team, Integer> {

	public List<Team> findByLeague(Short leagueId);
	
}