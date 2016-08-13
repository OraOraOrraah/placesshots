package com.kugiojotaro.placesshots.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.Fixture;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
	
	public List<Fixture> findByLeagueAndWeek(Short leagueId, Short week);
	
	public List<Fixture> findByLeague(Short leagueId);
	
	public List<Fixture> findByWeek(Short week);
	
	public List<Fixture> findByRound(String round);
	
}