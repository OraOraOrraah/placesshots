package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.entity.Predict;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("predictMapper")
public class PredictMapper  extends GenericMapperImpl<PredictDto, Predict>{
    
}