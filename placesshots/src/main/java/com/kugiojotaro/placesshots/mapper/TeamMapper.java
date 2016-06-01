package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.entity.Team;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("teamMapper")
public class TeamMapper  extends GenericMapperImpl<TeamDto, Team>{
    
}