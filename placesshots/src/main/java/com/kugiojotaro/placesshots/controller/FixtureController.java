package com.kugiojotaro.placesshots.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.entity.Fixture;
import com.kugiojotaro.placesshots.service.FixtureService;
import com.kugiojotaro.placesshots.service.LeagueService;
import com.kugiojotaro.placesshots.service.TeamService;
import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.util.Helper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value="/adm/fixture")
public class FixtureController {
	
	@Autowired
	private FixtureService fixtureService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private LeagueService leagueService;
	
	private Map<String,String> dropdownItem;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request) throws Exception {
		request.setAttribute("mode", Consts.MODE_ADD);
		request.setAttribute("fixtureDto", new FixtureDto());
		
		dropdownItem = new LinkedHashMap<String,String>();
		for (LeagueDto leagueDto : leagueService.findAll()) {
			dropdownItem.put((leagueDto.getId() + ""), leagueDto.getTitle());
		}
		modelMap.put("listLeague", dropdownItem);
		
		dropdownItem = new LinkedHashMap<String,String>();
		for (TeamDto teamDto : teamService.findByLeague((short) Consts.EURO_2016)) {////
			dropdownItem.put((teamDto.getId() + ""), teamDto.getTitle());
		}
		modelMap.put("listTeam", dropdownItem);
		
		return "fixture/form";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse save(@ModelAttribute("fixtureDto") FixtureDto fixtureDto, BindingResult result, @RequestParam(value = "mode", required = true) String mode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			if (mode.equals(Consts.MODE_ADD)) {
				fixtureDto.setCreateBy((String) request.getSession().getAttribute(Consts.AUTHEN_USER));
				fixtureService.create(fixtureDto);
			}
			else if (mode.equals(Consts.MODE_EDIT)) {
				fixtureDto.setUpdateBy((String) request.getSession().getAttribute(Consts.AUTHEN_USER));
				fixtureService.update(fixtureDto);
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value="/update_score", method=RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse updateScore(ModelMap modelMap, HttpServletRequest request) throws Exception {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			//FixtureDto fixtureDto = fixtureService.selectById(Helper.string2Long((String) request.getParameter("id")));
			FixtureDto fixtureDto = new FixtureDto();
			FixtureDto.builder().awayExtraTimeScore("").awayShortTitle("").awayTitle("");
			fixtureDto.setId((String) request.getParameter("id"));
			fixtureDto.setHomeScore((String) request.getParameter("homeScore"));
			fixtureDto.setAwayScore((String) request.getParameter("awayScore"));
			fixtureDto.setHomeExtraTimeScore((String) request.getParameter("homeExtraTimeScore"));
			fixtureDto.setAwayExtraTimeScore((String) request.getParameter("awayExtraTimeScore"));
			fixtureDto.setHomePenaltyScore((String) request.getParameter("homePenaltyScore"));
			fixtureDto.setAwayPenaltyScore((String) request.getParameter("awayPenaltyScore"));
			fixtureDto.setUpdateBy((String) request.getSession().getAttribute(Consts.AUTHEN_USER));
			fixtureService.updateScore(fixtureDto);
		}
		catch (Exception ex) {
			log.error(ex, ex);
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value="/list/{week}", method=RequestMethod.GET)
	public String list(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		request.setAttribute("listFixtureDto", fixtureService.findByLeagueAndWeek((short) Consts.EURO_2016, Helper.string2Short(week)));
		
		dropdownItem = new LinkedHashMap<String,String>();
		for (short i = 1; i<= (short) 23; i++) {
			dropdownItem.put((i + ""), (i + ""));
		}
		modelMap.put("listWeek", dropdownItem);
		
		request.setAttribute("week", week);
		
		return "fixture/load";
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String list(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "fixture/all";
	}
	
	@ResponseBody
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public DataTablesOutput<Fixture> load(@Valid DataTablesInput input) {
		log.info(" load : " + input);
		DataTablesOutput<Fixture> res = fixtureService.getFixture(input);
		return res;
	}
	
}