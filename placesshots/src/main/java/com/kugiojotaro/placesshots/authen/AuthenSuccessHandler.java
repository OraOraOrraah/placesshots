package com.kugiojotaro.placesshots.authen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.util.Consts;
import com.kugiojotaro.placesshots.dto.AuthUser;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.repository.UserRepository;

import lombok.extern.log4j.Log4j;

@Component("ajaxAuthenticationSuccessHandler")
@Log4j
public class AuthenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
		log.info(" onAuthenticationSuccess (username: " + auth.getName() + ")");
		
		User user = userRepository.findByUsername(auth.getName());
		req.getSession().setAttribute(Consts.AUTHEN_USER, AuthUser.builder().userId(user.getUserId()).username(user.getUsername()).displayName(user.getUserConnection() == null ? user.getUsername() : user.getUserConnection().getDisplayName()).imageURL(user.getUserConnection() == null ? "" : user.getUserConnection().getImageUrl()).build());
		
		resp.getWriter().print("success");
	}

}