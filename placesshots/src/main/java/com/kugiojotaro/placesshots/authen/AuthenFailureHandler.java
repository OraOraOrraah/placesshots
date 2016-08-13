package com.kugiojotaro.placesshots.authen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("ajaxAuthenticationFailureHandler")
@Log4j
public class AuthenFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, Authentication auth, AuthenticationException authEx) throws IOException, ServletException {
		log.info(" onAuthenticationFailure (auth: " + auth + ")");
	}
	
}