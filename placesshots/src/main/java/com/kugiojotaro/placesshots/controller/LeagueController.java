package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.service.LeagueService;

@Controller
@RequestMapping(value="/adm/league")
public class LeagueController {
	
	private static final Logger LOGGER = Logger.getLogger(LeagueController.class);
	
	@Autowired
	private LeagueService leagueService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request) throws Exception {
		LOGGER.info(" add");
		
		LeagueDto leagueDto = new LeagueDto();
		leagueDto.setTitle("Calcio");
		leagueService.create(leagueDto);
		
		return "home";
	}
	
}
