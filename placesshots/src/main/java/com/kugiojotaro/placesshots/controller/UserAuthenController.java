package com.kugiojotaro.placesshots.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kugiojotaro.placesshots.dto.AjaxJsonResponse;
import com.kugiojotaro.placesshots.dto.AuthUser;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.repository.UserRepository;
import com.kugiojotaro.placesshots.util.Consts;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserAuthenController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
//	@Autowired
//	private ConnectionRepository connectionRepository;
//	
//	private Facebook facebook;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpServletRequest request) throws Exception {
		return "login";
	}
	
	@RequestMapping(value = "/authenUser", method = RequestMethod.POST)
	public @ResponseBody AjaxJsonResponse authen(ModelMap modelMap, HttpServletRequest request) throws Exception {
		log.info(" authen: " + request.getParameter("username"));
		
		AjaxJsonResponse ajaxJsonResponse = new AjaxJsonResponse();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userRepository.findByUsername(username);
		if (user != null && user.getUsername().equals(username) && bcryptPasswordEncoder.matches(password, user.getPassword())) {
			List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
			listAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			if (username.equals("OraOraOrraah")) {
				listAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, listAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			request.getSession().setAttribute(Consts.AUTHEN_USER, AuthUser.builder().userId(user.getUserId()).username(user.getUsername()).displayName(user.getUserConnection() == null ? user.getUsername() : user.getUserConnection().getDisplayName()).imageURL(user.getUserConnection() == null ? "" : user.getUserConnection().getImageUrl()).build());
			
			ajaxJsonResponse.setResult(Consts.RESULT_SUCCESS);
		}
		else {
			ajaxJsonResponse.setResult(Consts.RESULT_FAIL);
		}
		
		return ajaxJsonResponse;
	}

	@RequestMapping(value = "/authUserInfo/{uuid}", method = RequestMethod.POST)
	public ModelAndView authUserInfo(HttpServletRequest request, @PathVariable String uuid) {
		AuthUser authUser = null;

		try {
			authUser = (AuthUser) request.getSession().getAttribute(Consts.AUTHEN_USER);
			request.setAttribute("displayName", authUser.getDisplayName());
			request.setAttribute("imageURL", authUser.getImageURL());
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
		}
		
		return new ModelAndView("user/info");
	}

//	@RequestMapping(value = "/fbLogin", method = RequestMethod.GET)
//	public void facebookConnect(HttpServletRequest request, HttpServletResponse response) {
//		log.info(" facebookConnect");
//		
//		facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
//        log.info(" " + facebook.userOperations().getUserProfile().getName());
//        log.info(" " + facebook.userOperations().getUserProfile().getEmail());
//        
//        try {
//			response.sendRedirect("/");
//		} catch (IOException e) {
//			log.error(e);
//		}
//	}
	
}