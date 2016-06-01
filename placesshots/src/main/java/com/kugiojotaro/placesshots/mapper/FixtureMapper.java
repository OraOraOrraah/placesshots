package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("fixtureMapper")
public class FixtureMapper  extends GenericMapperImpl<FixtureDto, Fixture>{
    
}