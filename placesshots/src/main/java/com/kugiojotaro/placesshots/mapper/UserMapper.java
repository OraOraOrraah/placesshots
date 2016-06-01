package com.kugiojotaro.placesshots.mapper;

import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.generic.GenericMapperImpl;

@Component("userMapper")
public class UserMapper  extends GenericMapperImpl<UserDto, User>{
    
}