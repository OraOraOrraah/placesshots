package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.service.LeagueService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/adm/league")
@Log4j
public class LeagueController {
	
	@Autowired
	private LeagueService leagueService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request) throws Exception {
		log.info(" add");
		
		LeagueDto leagueDto = new LeagueDto();
		leagueDto.setTitle("Calcio");
		leagueService.create(leagueDto);
		
		return "home";
	}
	
}