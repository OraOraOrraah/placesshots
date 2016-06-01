package com.kugiojotaro.placesshots.authen;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.kugiojotaro.placesshots.constant.PlaceShotsConstant;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.service.UserService;
import com.kugiojotaro.placesshots.util.Helper;

public class AuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = Logger.getLogger(AuthenSuccessHandler.class);
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
		LOGGER.info(" onAuthenticationSuccess (username: " + auth.getName() + ")");
		
		req.getSession().setAttribute(PlaceShotsConstant.SESSION_USER, auth.getName());
		
		//req.getSession().setAttribute(Constant.SESSION_USER_ITEMS, userService.selectUserItem(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		UserDto uDto = userService.findByUsername(auth.getName());
		
		//req.getSession().setAttribute(PlaceShotsConstant.SESSION_USER_ICON, Helper.null2Blank(uDto.getIcon()));
		
//		Map<String, String> mapUserIcon = (Map<String, String>) req.getSession().getAttribute("mapUserIcon");
//		if (mapUserIcon != null) {
//			if (mapUserIcon.get(auth.getName()) == null) {
//				UserDto userDto = userService.findByUsername(auth.getName());
//				if (userDto != null) {
//					mapUserIcon.put(userDto.getUsername(), userDto.getIcon());
//				}
//			} 
//		}
		
		super.onAuthenticationSuccess(req, resp, auth);
	}

}