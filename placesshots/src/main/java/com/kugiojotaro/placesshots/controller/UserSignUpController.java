package com.kugiojotaro.placesshots.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kugiojotaro.placesshots.util.Consts;

import lombok.extern.log4j.Log4j;

import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.service.UserService;

@Controller
@RequestMapping(value="/signup")
@Log4j
public class UserSignUpController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse checkUsername(HttpServletRequest request) {
		log.info(" checkUsername: " + request.getParameter("username"));
		
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			UserDto userDto = userService.findByUsername(request.getParameter("username"));
			if (userDto != null) {
				log.debug(" " + userDto.getUsername());
			}
			
			if (userDto != null && !userDto.getUsername().equals("") && userDto.getUsername().equalsIgnoreCase(request.getParameter("username"))) {
				ajaxJsonResponse.setReturnValue("Y");
			}
			else {
				ajaxJsonResponse.setReturnValue("N");
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse signup(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
		log.info(" signup: " + userDto.getUsername());
		
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		try {
			Map<String ,String> errorsMap = new LinkedHashMap<String, String>();
			
			if (bindingResult.hasErrors()) {
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				for (FieldError fieldError : fieldErrors) {
	                errorsMap.put(fieldError.getField(), messageSource.getMessage("validation." + fieldError.getCode().toString() + "." + fieldError.getField(), new Object[]{fieldError.getRejectedValue()}, Locale.ENGLISH));
	            }
				
				ajaxJsonResponse.setErrorsMap(errorsMap);
				ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
				
				return ajaxJsonResponse;
			}
			
			if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
				errorsMap.put("passwordConfirm", "password confirm not match!");
				ajaxJsonResponse.setErrorsMap(errorsMap);
				ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
				
				return ajaxJsonResponse;
			}
			
			userService.create(userDto);
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
}