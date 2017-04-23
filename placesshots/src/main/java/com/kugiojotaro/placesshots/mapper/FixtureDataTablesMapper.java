package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.FixtureDataTablesDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("fixtureDataTablesMapper")
public class FixtureDataTablesMapper  extends GenericMapperImpl<FixtureDataTablesDto, Fixture>{
    
}