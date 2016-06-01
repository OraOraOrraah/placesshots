package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kugiojotaro.placesshots.service.UserService;

@Controller
public class AuthenController {

	private static final Logger LOGGER = Logger.getLogger(AuthenController.class);

	@Autowired
	private UserService userService;
	
	//@Autowired
	//private Md5PasswordEncoder passwordEncoder;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "index";
	}

	@RequestMapping(value = "/authen/denied", method = RequestMethod.GET)
	public String denied() {
		return "denied";
	}

}