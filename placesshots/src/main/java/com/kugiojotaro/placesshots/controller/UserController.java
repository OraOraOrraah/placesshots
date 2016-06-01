package com.kugiojotaro.placesshots.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kugiojotaro.placesshots.util.Constant;
import com.kugiojotaro.placesshots.util.Helper;
import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.TeamDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.service.TeamService;
import com.kugiojotaro.placesshots.service.UserService;

@Controller
@RequestMapping(value="/")
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Map<String,String> radioItem;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(ModelMap modelMap, HttpServletRequest request) {
		radioItem = new LinkedHashMap<String,String>();
		radioItem.put("", "-");
		
		List<TeamDto> listTeamDto = teamService.findByLeague(PlaceShotsConstant.EURO_2016);
		for (TeamDto teamDto : listTeamDto) {
			radioItem.put(Helper.null2Blank(teamDto.getShortTitle()).toLowerCase(), "<img src= " + request.getContextPath() + PlaceShotsConstant.FLAG_PATH.replace("x", teamDto.getShortTitle().toLowerCase()) + " width=32 height=16 />");
		}
		
		request.setAttribute("radioItem", radioItem);
		request.setAttribute("mode", Constant.MODE_ADD);
		request.setAttribute("userDto", new UserDto());
		
		return "user/register";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse registerPost(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			Map<String ,String> errorsMap = new LinkedHashMap<String, String>();
			
			if (bindingResult.hasErrors()) {
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				for (FieldError fieldError : fieldErrors) {
	                errorsMap.put(fieldError.getField(), messageSource.getMessage("validation." + fieldError.getCode().toString() + "." + fieldError.getField(), new Object[]{fieldError.getRejectedValue()}, Locale.ENGLISH));
	            }
				
				ajaxJsonResponse.setErrorsMap(errorsMap);
				ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
				
				return ajaxJsonResponse;
			}
			
			userService.create(userDto);
			
//			Map<String, String> mapUserIcon = (Map<String, String>) request.getServletContext().getAttribute("mapUserIcon");
//			if (mapUserIcon != null) {
//				if (mapUserIcon.get((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER)) == null) {
//					mapUserIcon.put(userDto.getUsername(), userDto.getIcon());
//				} 
//			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(ModelMap modelMap, HttpServletRequest request) {
		return "user/changepassword";
	}
	
	@RequestMapping(value = "/changepassword/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse changePasswordSave(HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			UserDto userDto = new UserDto();
			userDto.setUsername((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
			userDto.setPassword(request.getParameter("password"));
			userService.changepassword(userDto);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/adm/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap modelMap, HttpServletRequest request) {
		return "user/forgotpassword";
	}
	
	@RequestMapping(value = "/adm/forgotpassword/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse forgotPasswordSave(HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			String password = RandomStringUtils.randomAlphanumeric(8);
			
			UserDto userDto = new UserDto();
			userDto.setUsername(request.getParameter("username"));
			userDto.setPassword(password);
			userService.changepassword(userDto);
			
			ajaxJsonResponse.setReturnValue(password);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse checkUser(HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			UserDto userDto = userService.findByUsername(request.getParameter("username"));
			if (userDto != null) {
				LOGGER.info(" " + userDto.getUsername());
			}
			
			if (userDto != null && !userDto.getUsername().equals("") && userDto.getUsername().equals(request.getParameter("username"))) {
				ajaxJsonResponse.setReturnValue("Y");
			}
			else {
				ajaxJsonResponse.setReturnValue("N");
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap modelMap, HttpServletRequest request) {
		
		radioItem = new LinkedHashMap<String,String>();
		radioItem.put("", "-");
		
		List<TeamDto> listTeamDto = teamService.findByLeague(PlaceShotsConstant.EURO_2016);
		for (TeamDto teamDto : listTeamDto) {
			radioItem.put(Helper.null2Blank(teamDto.getShortTitle()).toLowerCase(), "<img src= " + request.getContextPath() + PlaceShotsConstant.FLAG_PATH.replace("x", teamDto.getShortTitle().toLowerCase()) + " width=32 height=16 />");
		}
		
		UserDto userDto = userService.findByUsername((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
		
		request.setAttribute("radioItem", radioItem);
		request.setAttribute("userDto", userDto);
		
		return "user/profile";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/profile/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse profileSave(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			userDto.setUsername((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER));
			userService.updateProfile(userDto);
			
			Map<String, String> mapUserIcon = (Map<String, String>) request.getServletContext().getAttribute("mapUserIcon");
			if (mapUserIcon != null) {
				if (userDto.getIcon() == null || (userDto.getIcon() != null && userDto.getIcon().equals(""))) {
					if (mapUserIcon.get((String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER)) != null) {
						mapUserIcon.remove(userDto.getUsername());
					}
				}
				else {
					mapUserIcon.put(userDto.getUsername(), userDto.getIcon());
					
					//request.getSession().setAttribute(PlaceShotsConstant.SESSION_USER_ICON, userDto.getIcon());
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Constant.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/user/icon", method = RequestMethod.POST)
	public @ResponseBody String userIcon(HttpServletRequest request) {
		String icon = "";

		try {
			@SuppressWarnings("unchecked")
			Map<String, String> mapUserIcon = (Map<String, String>) request.getServletContext().getAttribute("mapUserIcon");
			if (mapUserIcon != null) {
				if (!Helper.null2Blank(mapUserIcon.get(request.getParameter("username"))).equals("")) {
					icon = request.getContextPath() + PlaceShotsConstant.FLAG_PATH.replace("x", mapUserIcon.get(request.getParameter("username")));
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return icon;
	}
	
	@RequestMapping(value = "/user/logined/{uuid}", method = RequestMethod.POST)
	public @ResponseBody String userLogined(HttpServletRequest request, @PathVariable String uuid) {
		//LOGGER.info(" uuid: " + uuid);
		String result = "";

		try {
			result = (String) request.getSession().getAttribute(PlaceShotsConstant.SESSION_USER);
			LOGGER.info(" result: " + result);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		return result;
	}
	
}