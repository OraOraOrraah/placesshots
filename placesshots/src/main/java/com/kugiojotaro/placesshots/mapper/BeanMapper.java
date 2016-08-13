package com.kugiojotaro.placesshots.mapper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("BeanMapper")
public class BeanMapper<T extends Serializable, R extends Serializable>  extends GenericMapperImpl<T, R> {
    
}