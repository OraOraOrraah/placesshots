package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/")
@Log4j
public class MainController {
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "main";
	}
	
}