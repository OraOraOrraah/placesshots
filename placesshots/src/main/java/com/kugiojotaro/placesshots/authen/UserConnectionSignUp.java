package com.kugiojotaro.placesshots.authen;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserConnectionSignUp implements ConnectionSignUp {
	
    public String execute(Connection<?> connection) {
    	log.info(" SignUp: " + connection.getKey().getProviderUserId());
    	return connection.getKey().getProviderUserId();
    }
	
}