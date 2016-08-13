package com.kugiojotaro.placesshots.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.LeagueDto;
import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.service.LeagueService;
import com.kugiojotaro.placesshots.service.TeamService;
import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.util.Helper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/adm/team")
@Log4j
public class TeamController {
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private TeamService teamService;

	private Map<String,String> dropdownItem;
	
	private void initDropdownItem(ModelMap modelMap) {
		List<LeagueDto> listLeagueDto = leagueService.findAll();
		
		dropdownItem = new LinkedHashMap<String,String>();
		for (LeagueDto leagueDto : listLeagueDto) {
			dropdownItem.put((leagueDto.getId() + ""), leagueDto.getTitle());
		}
		modelMap.put("listLeague", dropdownItem);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request) throws Exception {
		log.info(" add");
		
		initDropdownItem(modelMap);
		request.setAttribute("mode", Consts.MODE_ADD);
		request.setAttribute("teamDto", new TeamDto());
		
		teamService.create(null);
		
		return "team/form";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(@RequestParam(value = "id", required = true) String id, ModelMap modelMap, HttpServletRequest request) throws Exception {
		log.info(" edit");
		
		initDropdownItem(modelMap);
		request.setAttribute("mode", Consts.MODE_EDIT);
		request.setAttribute("teamDto", teamService.selectById(Helper.string2Integer(id)));
		
		return "team/form";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse save(@ModelAttribute("teamDTO") TeamDto teamDto, BindingResult result, @RequestParam(value = "mode", required = true) String mode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		log.info(" save");
		
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			if (mode.equals(Consts.MODE_ADD)) {
				teamService.create(teamDto);
			}
			else if (mode.equals(Consts.MODE_EDIT)) {
				teamService.update(teamDto);
			}
		}
		catch (Exception ex) {
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
}