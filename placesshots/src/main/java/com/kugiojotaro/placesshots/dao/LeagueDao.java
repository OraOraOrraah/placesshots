package com.kugiojotaro.placesshots.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kugiojotaro.placesshots.entity.League;

public interface LeagueDao extends JpaRepository<League, Short> {

}