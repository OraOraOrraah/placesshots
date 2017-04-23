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
import com.kugiojotaro.placesshots.dto.UserChangePasswordDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.service.UserService;

@Controller
@RequestMapping(value="/")
@Log4j
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Map<String,String> radioItem;
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(ModelMap modelMap, HttpServletRequest request) {
		request.setAttribute("userChangePasswordDto", new UserChangePasswordDto());
		return "user/changepassword";
	}
	
	@RequestMapping(value = "/changepassword/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse changePasswordSave(@Valid @ModelAttribute("userChangePasswordDto") UserChangePasswordDto userChangePasswordDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
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

			if (!userChangePasswordDto.getPassword().equals(userChangePasswordDto.getPasswordConfirm())) {
				errorsMap.put("passwordConfirm", "password confirm not match!");
				ajaxJsonResponse.setErrorsMap(errorsMap);
				ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
				
				return ajaxJsonResponse;
			}
			
			userChangePasswordDto.setUserId(getAuthId(request));
			Boolean result = userService.changepassword(userChangePasswordDto);
			if (result) {
				ajaxJsonResponse.setResult(Consts.RESULT_SUCCESS);
			}
			else {
				ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap modelMap, HttpServletRequest request) {
		return "user/forgotpassword";
	}
	
	@RequestMapping(value = "/adm/forgotpassword", method = RequestMethod.GET)
	public String forgotPasswordAdm(ModelMap modelMap, HttpServletRequest request) {
		request.setAttribute("userDto", new UserDto());
		return "user/forgotpasswordAdm";
	}
	
	@RequestMapping(value = "/adm/forgotpassword/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse forgotPasswordAdmSave(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
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
			
			userService.update(userDto);
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
	
	
	@RequestMapping(value = "/checkUserPassword", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse checkUserPassword(HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			UserDto userDto = userService.findByUsernameAndPassword(getAuthUsername(request), request.getParameter("oldPassword"));
			if (userDto != null) {
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
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap modelMap, HttpServletRequest request) {
		UserDto userDto = userService.findByUserId(getAuthId(request));
		
		request.setAttribute("radioItem", radioItem);
		request.setAttribute("userDto", userDto);
		
		return "user/profile";
	}
	
	@RequestMapping(value = "/profile/save", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse profileSave(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();

		try {
			userDto.setUserId(getAuthId(request) + "");
			userService.updateProfile(userDto);
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}
	
}