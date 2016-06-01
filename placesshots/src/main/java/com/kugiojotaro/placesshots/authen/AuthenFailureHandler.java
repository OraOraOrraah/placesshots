package com.kugiojotaro.placesshots.authen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.kugiojotaro.placesshots.dao.UserDao;

public class AuthenFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static final Logger LOGGER = Logger.getLogger(AuthenFailureHandler.class);
	
	private UserDao userDao;
	
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth) throws IOException, ServletException {
		String username = (String)req.getParameter("j_username");
		LOGGER.info(" username: " + username);

		super.onAuthenticationFailure(req, resp, auth);
	}
	
}