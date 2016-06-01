package com.kugiojotaro.placesshots.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kugiojotaro.placesshots.util.Constant;
import com.kugiojotaro.placesshots.util.Helper;
import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.FixtureDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictChampionDto;
import com.kugiojotaro.placesshots.dto.PredictDisplayDto;
import com.kugiojotaro.placesshots.dto.PredictDto;
import com.kugiojotaro.placesshots.dto.PredictResultDetailDto;
import com.kugiojotaro.placesshots.dto.PredictResultDto;
import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.dto.UserPointDto;
import com.kugiojotaro.placesshots.service.PredictService;
import com.kugiojotaro.placesshots.service.FixtureService;
import com.kugiojotaro.placesshots.service.TeamService;

@Controller
@RequestMapping(value="/predict")
public class PredictController {
	
	private static final Logger LOGGER = Logger.getLogger(PredictController.class);
	
	@Autowired
	private FixtureService fixtureService;
	
	@Autowired
	private PredictService predictService;
	
	@Autowired
	private TeamService teamService;
	
	private Map<String,String> dropdownItem;
	
	private void indexInit(ModelMap modelMap, HttpServletRequest request, String week) {
		
		String week2 = week;
		if (week2.equals("0")) {
			week2 = "23";
		}
		
		List<PredictDisplayDto> listPredictDisplayDto = new ArrayList<PredictDisplayDto>();
		List<FixtureDto> listFixtureDto = fixtureService.findByLeagueAndWeek((short) PlaceShotsConstant.EURO_2016, Helper.string2Short(week2));
		List<PredictDto> listPredictDto = predictService.findByUserAndWeek((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER), Helper.string2Short(week2));
		
		int i = 1;
		for (FixtureDto fixtureDto : listFixtureDto) {
			PredictDisplayDto predictDisplayDto = new PredictDisplayDto();
			predictDisplayDto.setNo(i + "");
			predictDisplayDto.setFixtureId(fixtureDto.getId());
			predictDisplayDto.setHomeTitle(Helper.null2Blank(fixtureDto.getHomeTitle()));
			predictDisplayDto.setHomeShortTitle(Helper.null2Blank(fixtureDto.getHomeShortTitle()));
			predictDisplayDto.setAwayTitle(Helper.null2Blank(fixtureDto.getAwayTitle()));
			predictDisplayDto.setAwayShortTitle(Helper.null2Blank(fixtureDto.getAwayShortTitle()));
			
			for (PredictDto predictDto: listPredictDto) {
				if (fixtureDto.getId().equals(predictDto.getFixtureId())) {
					predictDisplayDto.setHomeScore(predictDto.getHomeScore());
					predictDisplayDto.setAwayScore(predictDto.getAwayScore());
					break;
				}
			}
			
			listPredictDisplayDto.add(predictDisplayDto);
			
			i++;
		}
		
		request.setAttribute("week", week2);
		request.setAttribute("listPredictDisplayDto", listPredictDisplayDto);
		
		if (week.equals("0")) {
			request.setAttribute("live", "0");
		}
		else {
			if (Helper.string2Short(week) < (Helper.string2Short((String) request.getServletContext().getAttribute(PlaceShotsConstant.WEEK)))) {
				request.setAttribute("live", "0");
			}
			else {
				if (listFixtureDto != null && listFixtureDto.size() > 0) {
					request.setAttribute("live", "1");
				}
				else {
					request.setAttribute("live", "0");
				}
			}
		}
		

		dropdownItem = new LinkedHashMap<String,String>();
		/*
		for (short j = 1; j<= (short) 25; j++) {
			dropdownItem.put((j + ""), (j + ""));
		}
		*/
		
		dropdownItem.put("1", "Friday 10th June 2016");
		dropdownItem.put("2", "Saturday 11th June 2016");
		dropdownItem.put("3", "Sunday 12th June 2016");
		dropdownItem.put("4", "Monday 13th June 2016");
		dropdownItem.put("5", "Tuesday 14th June 2016");
		dropdownItem.put("6", "Wednesday 15th June 2016");
		dropdownItem.put("7", "Thursday 16th June 2016");
		dropdownItem.put("8", "Friday 17th June 2016");
		dropdownItem.put("9", "Saturday 18th June 2016");
		dropdownItem.put("10", "Sunday 19th June 2016");
		dropdownItem.put("11", "Monday 20th June 2016");
		dropdownItem.put("12", "Tuesday 21st June 2016");
		dropdownItem.put("13", "Wednesday 22nd June 2016");
		dropdownItem.put("14", "Saturday 25th June 2016");
		dropdownItem.put("15", "Sunday 26th June 2016");
		dropdownItem.put("16", "Monday 27th June 2016");
		dropdownItem.put("17", "Thursday 30th June 2016");
		dropdownItem.put("18", "Friday 1st July 2016");
		dropdownItem.put("19", "Saturday 2nd July 2016");
		dropdownItem.put("20", "Sunday 3rd July 2016");
		dropdownItem.put("21", "Wednesday 6th July 2016");
		dropdownItem.put("22", "Thursday 7th July 2016");
		dropdownItem.put("23", "Sunday 10th July 2016");
		
		modelMap.put("listWeek", dropdownItem);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index1(ModelMap modelMap, HttpServletRequest request) throws Exception {
		indexInit(modelMap, request, ((String) request.getServletContext().getAttribute("week")));
		
		return "predict/index";
	}
	
	@RequestMapping(value = "/index/{week}", method = RequestMethod.GET)
	public String index2(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		indexInit(modelMap, request, week);
		
		return "predict/index";
	}
	
//	@RequestMapping(value = "/extra", method = RequestMethod.GET)
//	public String extra(ModelMap modelMap, HttpServletRequest request) throws Exception {
//		LOGGER.info(" extra : " + ((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER)));
//		
//		String predictedChampionTeam = "";
//		String predictedChampionRound = "";
//			
//		//
//		PredictChampionDto predictChampionDto = predictService.findPredictChampionByUser((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
//
//		String enableSave = "1";
//		String prediected = "N";
//		
//		if (predictChampionDto != null && !predictChampionDto.getRound().equals((String) request.getServletContext().getAttribute(PlaceShotsConstant.ROUND))) {
//			
//			prediected = "Y";
//			
//			//
//			TeamDto teamDto = teamService.selectById(Helper.string2Integer(predictChampionDto.getTeamId()));
//			
//			//
//			predictedChampionTeam = teamDto.getShortTitle();
//			predictedChampionRound = predictChampionDto.getRound();
//			
//		}
//		
//		if (predictChampionDto == null) {
//			 predictChampionDto = new PredictChampionDto();
//		}
//			
//		Map<String,String> dropdownItem2 = new LinkedHashMap<String,String>();
//			
//		if (prediected.equals("N")) {
//			//
//			List<FixtureDto> listFixtureDto = fixtureService.findByRound(Helper.string2Short((String) request.getServletContext().getAttribute(PlaceShotsConstant.ROUND)));
//			for (FixtureDto fixtureDto : listFixtureDto) {
//				dropdownItem2.put(fixtureDto.getHomeId(), fixtureDto.getHomeTitle());
//				dropdownItem2.put(fixtureDto.getAwayId(), fixtureDto.getAwayTitle());
//			}
//		}
//		
//		if (Helper.string2Short((String) request.getServletContext().getAttribute(PlaceShotsConstant.ROUND)).equals("0")) {
//			enableSave = "0";
//		}
//		
//		if (dropdownItem2.size() == 0) {
//			enableSave = "0";
//		}
//		
//		//
//		predictChampionDto.setRound((String) request.getServletContext().getAttribute(PlaceShotsConstant.ROUND));
//		
//		LOGGER.info(" enableSave: " + enableSave);
//		LOGGER.info(" prediected: " + prediected);
//		
//		//
//		modelMap.put("predictChampionDto", predictChampionDto);
//		modelMap.put("enableSave", enableSave);
//		modelMap.put("prediected", prediected);
//		modelMap.put("listTeam", dropdownItem2);
//		modelMap.put("predictedChampionTeam", predictedChampionTeam);
//		modelMap.put("predictedChampionRound", predictedChampionRound);
//			
//		return "predict/extra";
//	}
//	
//	@RequestMapping(value = "/extrasave", method = RequestMethod.POST)
//	public @ResponseBody AjaxJsonResponse saveChampion(@ModelAttribute("predictChampionDto") PredictChampionDto predictChampionDto, HttpServletRequest request) {
//		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
//		
//		try {
//			if (!predictChampionDto.getRound().equals((String) request.getServletContext().getAttribute("round"))) {
//				ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
//				return ajaxJsonResponse;
//			}
//			
//			predictChampionDto.setUser((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
//			predictService.predictChampion(predictChampionDto);
//			
//			ajaxJsonResponse.setResult(Constant.RESULT_SUCCESS);
//		}
//		catch (Exception ex) {
//			LOGGER.error(ex.getMessage());
//		
//			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
//		}
//		
//		return ajaxJsonResponse;
//	}
	
	/*
	@RequestMapping(value = "/data/{week}/{uuid}", method = RequestMethod.POST)
	public String data(ModelMap modelMap, HttpServletRequest request, @PathVariable String week, @PathVariable String uuid) throws Exception {
		indexInit(modelMap, request, week);
	    
	    return "predict/data"; 
	}
	*/

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse save(HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			LOGGER.info(" cur week: " + request.getServletContext().getAttribute("week"));
			LOGGER.info(" req week: " + request.getParameter("week"));
			//if (((String) request.getServletContext().getAttribute("week")).equals(request.getParameter("week"))) {
			if (Helper.string2Integer(request.getParameter("week")) >= (Helper.string2Integer((String) request.getServletContext().getAttribute("week")))) {
				List<PredictDto> listPredictDto = new ArrayList<>();
				
				String[] fixtureIds = request.getParameterValues("fixtureId");
				String[] homeScores = request.getParameterValues("homeScore");
				String[] awayScores = request.getParameterValues("awayScore");
				String[] redCardFlags = request.getParameterValues("redCardFlag");
				
				for (int i = 0; i < fixtureIds.length; i++) {
					if (homeScores[i] != "" && awayScores[i] != "") {
						PredictDto predictDto = new PredictDto();
						predictDto.setWeek(request.getParameter("week"));
						predictDto.setFixtureId(fixtureIds[i]);
						predictDto.setHomeScore(homeScores[i]);
						predictDto.setAwayScore(awayScores[i]);
						/*if (redCardFlags[i].equals("1")) {
							predictDto.setRedCardFlag("1");
						}*/
						predictDto.setUser((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
						
						listPredictDto.add(predictDto);
					}
				}
				
				predictService.predict(listPredictDto);
			}
			else {
				LOGGER.info(" week change");
				ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
			}
			
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/rule", method = RequestMethod.GET)
	public String rule(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "predict/rule";
	}
	
	@SuppressWarnings("unchecked")
	private void resultInit(String week, ModelMap modelMap, HttpServletRequest request) {
		//
		List<FixtureDto> listFixtureDto = fixtureService.findByLeagueAndWeek ((short) PlaceShotsConstant.EURO_2016, Helper.string2Short(week));
		
		List<PredictResultDto> listPredictResultHeaderDto = new ArrayList<PredictResultDto>();
		PredictResultDto header = new PredictResultDto();
		header.setPoint(0);
		for (FixtureDto fixtureDto : listFixtureDto) {
			PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
			predictResultDetailDto.setNo(fixtureDto.getHomeShortTitle() + "-" + fixtureDto.getAwayShortTitle() + "<br/>" + Helper.nullObj2Blank(fixtureDto.getHomeScore()) + " - " + Helper.nullObj2Blank(fixtureDto.getAwayScore()));
			predictResultDetailDto.setHomeTitle(fixtureDto.getHomeTitle());
			predictResultDetailDto.setHomeShortTitle(fixtureDto.getHomeShortTitle());
			predictResultDetailDto.setAwayTitle(fixtureDto.getAwayTitle());
			predictResultDetailDto.setAwayShortTitle(fixtureDto.getAwayShortTitle());
			predictResultDetailDto.setHomeScore(Helper.nullObj2Blank(fixtureDto.getHomeScore()));
			predictResultDetailDto.setAwayScore(Helper.nullObj2Blank(fixtureDto.getAwayScore()));
			predictResultDetailDto.setHomeExtraTimeScore(Helper.nullObj2Blank(fixtureDto.getHomeExtraTimeScore()));
			predictResultDetailDto.setAwayExtraTimeScore(Helper.nullObj2Blank(fixtureDto.getAwayExtraTimeScore()));
			if (!Helper.nullObj2Blank(fixtureDto.getHomeExtraTimeScore()).equals("")) {
				predictResultDetailDto.setHomeTotalScore((Helper.string2Integer(predictResultDetailDto.getHomeScore()) + Helper.string2Integer(predictResultDetailDto.getHomeExtraTimeScore())) + "");
				predictResultDetailDto.setAwayTotalScore((Helper.string2Integer(predictResultDetailDto.getAwayScore()) + Helper.string2Integer(predictResultDetailDto.getAwayExtraTimeScore())) + "");
			}
			predictResultDetailDto.setHomePenaltyScore(Helper.nullObj2Blank(fixtureDto.getHomePenaltyScore()));
			predictResultDetailDto.setAwayPenaltyScore(Helper.nullObj2Blank(fixtureDto.getAwayPenaltyScore()));
			header.getListPredictResultDetailDto().add(predictResultDetailDto);
		}
		listPredictResultHeaderDto.add(header);
				
		List<PredictResultDto> listPredictResultDto = predictService.weeklyResult(Helper.string2Short(week));
		Comparator<PredictResultDto> comparator = new Comparator<PredictResultDto>() {
		    public int compare(PredictResultDto obj1, PredictResultDto obj2) {
		        return obj2.getPoint() - obj1.getPoint();
		    }
		};
		Collections.sort(listPredictResultDto, comparator);
		
		//
//		Map<String, String> mapUserIcon = (Map<String, String>) request.getServletContext().getAttribute(PlaceShotsConstant.MAP_USER_ICON);
//		if (mapUserIcon != null) {
//			for (PredictResultDto predictResultDto : listPredictResultDto) {
//				predictResultDto.setUserIcon(Helper.null2Blank(mapUserIcon.get(predictResultDto.getUsername())));
//			}
//		}
		
		request.setAttribute("week", week);
		request.setAttribute("listPredictResultHeaderDto", listPredictResultHeaderDto);
		request.setAttribute("listPredictResultDto", listPredictResultDto);
		request.setAttribute("listFixtureDto", listFixtureDto);
		
		/*
		dropdownItem = new LinkedHashMap<String,String>();
		for (short j = 1; j<= (short) 25; j++) {
			dropdownItem.put((j + ""), (j + ""));
		}
		*/
		
		dropdownItem = new LinkedHashMap<String,String>();
		
		dropdownItem.put("1", "Friday 10th June 2016");
		dropdownItem.put("2", "Saturday 11th June 2016");
		dropdownItem.put("3", "Sunday 12th June 2016");
		dropdownItem.put("4", "Monday 13th June 2016");
		dropdownItem.put("5", "Tuesday 14th June 2016");
		dropdownItem.put("6", "Wednesday 15th June 2016");
		dropdownItem.put("7", "Thursday 16th June 2016");
		dropdownItem.put("8", "Friday 17th June 2016");
		dropdownItem.put("9", "Saturday 18th June 2016");
		dropdownItem.put("10", "Sunday 19th June 2016");
		dropdownItem.put("11", "Monday 20th June 2016");
		dropdownItem.put("12", "Tuesday 21st June 2016");
		dropdownItem.put("13", "Wednesday 22nd June 2016");
		dropdownItem.put("14", "Saturday 25th June 2016");
		dropdownItem.put("15", "Sunday 26th June 2016");
		dropdownItem.put("16", "Monday 27th June 2016");
		dropdownItem.put("17", "Thursday 30th June 2016");
		dropdownItem.put("18", "Friday 1st July 2016");
		dropdownItem.put("19", "Saturday 2nd July 2016");
		dropdownItem.put("20", "Sunday 3rd July 2016");
		dropdownItem.put("21", "Wednesday 6th July 2016");
		dropdownItem.put("22", "Thursday 7th July 2016");
		dropdownItem.put("23", "Sunday 10th July 2016");
		modelMap.put("listWeek", dropdownItem);
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result1(ModelMap modelMap, HttpServletRequest request) throws Exception {
		
		String week = (String) request.getServletContext().getAttribute("week");
		LOGGER.info(" context week: " + week);
		
		if (week.equals("0")) {
			week = "23";
		}
		else {
			if ((boolean) request.getServletContext().getAttribute(PlaceShotsConstant.LIVE)) {
				week = (Integer.parseInt(week) - 1) + "";
			}
		}
		
		LOGGER.info(" result of week: " + week);
		
		resultInit(week, modelMap, request);
		
		return "predict/result";
	}
	
	@RequestMapping(value = "/result/{week}", method = RequestMethod.GET)
	public String result2(ModelMap modelMap, HttpServletRequest request, @PathVariable String week) throws Exception {
		
		LOGGER.info(" result of week: " + request.getServletContext().getAttribute("week"));
		resultInit(week, modelMap, request);
		
		return "predict/result";
	}
	
//	@RequestMapping(value = "/extraresult", method = RequestMethod.GET)
//	public ModelAndView extraResult(Model model) throws Exception {
//		//
//		List<PredictChampionDisplayDto> listPredictChampionDisplayDto = predictService.resultPredictChampion();
//			
//		model.addAttribute("listPredictChampionDisplayDto", listPredictChampionDisplayDto);
//		
//		return new ModelAndView("predict/extraresult");
//	}

	/*
	@RequestMapping(value = "/myresult", method = RequestMethod.GET)
	public String myresult(ModelMap modelMap, HttpServletRequest request) throws Exception {
		LOGGER.info(" myresult: " + ((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER)));
		
		//
		List<FixtureDto> listFixtureDto = fixtureService.findByLeague(PlaceShotsConstant.WORLD_CUP_2014);
		
		List<PredictResultDto> listPredictResultHeaderDto = new ArrayList<PredictResultDto>();
		PredictResultDto header = new PredictResultDto();
		header.setPoint(0);
		for (FixtureDto fixtureDto : listFixtureDto) {
			PredictResultDetailDto predictResultDetailDto = new PredictResultDetailDto();
			predictResultDetailDto.setNo(fixtureDto.getHomeShortTitle() + "-" + fixtureDto.getAwayShortTitle() + "<br/>" + Helper.nullObj2Blank(fixtureDto.getHomeScore()) + " - " + Helper.nullObj2Blank(fixtureDto.getAwayScore()));
			predictResultDetailDto.setHomeTitle(fixtureDto.getHomeTitle());
			predictResultDetailDto.setHomeShortTitle(fixtureDto.getHomeShortTitle());
			predictResultDetailDto.setAwayTitle(fixtureDto.getAwayTitle());
			predictResultDetailDto.setAwayShortTitle(fixtureDto.getAwayShortTitle());
			predictResultDetailDto.setHomeScore(Helper.nullObj2Blank(fixtureDto.getHomeScore()));
			predictResultDetailDto.setAwayScore(Helper.nullObj2Blank(fixtureDto.getAwayScore()));
			predictResultDetailDto.setHomeExtraTimeScore(Helper.nullObj2Blank(fixtureDto.getHomeExtraTimeScore()));
			predictResultDetailDto.setAwayExtraTimeScore(Helper.nullObj2Blank(fixtureDto.getAwayExtraTimeScore()));
			if (!Helper.nullObj2Blank(fixtureDto.getHomeExtraTimeScore()).equals("")) {
				predictResultDetailDto.setHomeTotalScore((Helper.string2Integer(predictResultDetailDto.getHomeScore()) + Helper.string2Integer(predictResultDetailDto.getHomeExtraTimeScore())) + "");
				predictResultDetailDto.setAwayTotalScore((Helper.string2Integer(predictResultDetailDto.getAwayScore()) + Helper.string2Integer(predictResultDetailDto.getAwayExtraTimeScore())) + "");
			}
			predictResultDetailDto.setHomePenaltyScore(Helper.nullObj2Blank(fixtureDto.getHomePenaltyScore()));
			predictResultDetailDto.setAwayPenaltyScore(Helper.nullObj2Blank(fixtureDto.getAwayPenaltyScore()));
			header.getListPredictResultDetailDto().add(predictResultDetailDto);
		}
		listPredictResultHeaderDto.add(header);
				
		List<PredictResultDto> listPredictResultDto = predictService.result(((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER)));
		
		request.setAttribute("listPredictResultDto", listPredictResultDto);
		request.setAttribute("listFixtureDto", listFixtureDto);
		
		return "predict/myresult";
	}
	*/
	
	@RequestMapping(value = "/standing", method = RequestMethod.GET)
	public String standing(ModelMap modelMap, HttpServletRequest request) throws Exception {
		Short week = (short) (Helper.string2Short((String) request.getServletContext().getAttribute("week")) - 1);
		
		//
		List<UserPointDto> listUserPointDto = predictService.standing(week);
		
		//
		Comparator<UserPointDto> comparator = new Comparator<UserPointDto>() {
		    public int compare(UserPointDto obj1, UserPointDto obj2) {
		        //return obj2.getPoint() - obj1.getPoint();
		        int result = obj2.getTotalPoint() - obj1.getTotalPoint();
		        if (result != 0) {
		            return result;
		        }
		        result = obj2.getCorrectResultAndScore() - obj1.getCorrectResultAndScore();
		        if (result != 0) {
		            return result;
		        }
		        result = obj2.getCorrectResult() - obj1.getCorrectResult();
		        if (result != 0) {
		            return result;
		        }
		        return  obj2.getIncorrectResult() - obj1.getIncorrectResult();
		    }
		    
		};
		
		//
		Collections.sort(listUserPointDto, comparator);
		
		//
		int rank = 1;
		int point = 0;
		int rec = 0;
		
		for (UserPointDto userPointDto : listUserPointDto) {
			if (userPointDto.getTotalPoint() < point) {
				rank = rank + rec;
				rec = 0;
			}
			
			userPointDto.setRank(rank + "");
			
			point = userPointDto.getTotalPoint();
			rec++;
		}
		
//		//
//		Map<String, String> mapUserIcon = (Map<String, String>) request.getServletContext().getAttribute(PlaceShotsConstant.MAP_USER_ICON);
//		if (mapUserIcon != null) {
//			for (UserPointDto userPointDto : listUserPointDto) {
//				userPointDto.setUserIcon(Helper.null2Blank(mapUserIcon.get(userPointDto.getUsername())));
//			}
//		}
		
		request.setAttribute("listUserPointDto", listUserPointDto);
		
		return "predict/standing";
	}
}