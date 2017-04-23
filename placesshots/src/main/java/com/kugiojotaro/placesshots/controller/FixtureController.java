package com.kugiojotaro.placesshots.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.kugiojotaro.placesshots.dto.FixtureDataTablesDto;
import com.kugiojotaro.placesshots.service.FixtureService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value="/fixture")
public class FixtureController {
	
	@Autowired
	private FixtureService fixtureService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "fixture/list";
	}
	
	@ResponseBody
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public DataTablesOutput<FixtureDataTablesDto> load(@Valid DataTablesInput input, HttpServletRequest request) {
		log.info(" load: " + request.getParameter("params"));
		//
		//DataTablesUtil.mapCustomSearchParameter(input, request.getParameter("params"));
		//
//		if (input.getCustomSearchs().size() == 0) {
//			return new DataTablesOutput<FixtureDataTablesDto>();
//		}
//		else {
//			return fixtureService.getFixture(input);
//		}
		return fixtureService.getFixture(input);
	}
	
}