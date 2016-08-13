package com.kugiojotaro.placesshots.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j;

import com.kugiojotaro.placesshots.dto.AuthUser;
import com.kugiojotaro.placesshots.util.Consts;

@Controller
@Log4j
public class BaseController {
	
	public String getAuthUsername(HttpServletRequest request) {
		AuthUser authUser = (AuthUser) request.getSession().getAttribute(Consts.AUTHEN_USER);
		return authUser.getUsername();
	}
	
}