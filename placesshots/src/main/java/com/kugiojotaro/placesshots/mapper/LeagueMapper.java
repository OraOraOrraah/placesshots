package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.entity.League;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("leagueMapper")
public class LeagueMapper  extends GenericMapperImpl<LeagueDto, League>{
    
}