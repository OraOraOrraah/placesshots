package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class MainController {
	
	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "main";
	}
	
}