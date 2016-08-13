package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.League;

public interface LeagueRepository extends JpaRepository<League, Short> {
	
}