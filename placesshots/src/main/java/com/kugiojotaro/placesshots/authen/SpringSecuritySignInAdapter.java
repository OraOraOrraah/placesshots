package com.kugiojotaro.placesshots.authen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.kugiojotaro.placesshots.dto.AuthUser;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.entity.UserConnection;
import com.kugiojotaro.placesshots.entity.UserConnectionId;
import com.kugiojotaro.placesshots.repository.UserConnectionRepository;
import com.kugiojotaro.placesshots.repository.UserCustomRepository;
import com.kugiojotaro.placesshots.service.UserService;
import com.kugiojotaro.placesshots.util.Consts;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SpringSecuritySignInAdapter implements SignInAdapter {

	@Autowired
	private UserCustomRepository userCustomRepository;
	
	@Autowired
	private UserConnectionRepository userConnectionRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest nativeWebRequest) {
		try {
			log.info(" SignIn: ");
			
			ConnectionKey connectionKey = connection.getKey();
			
			User user = userCustomRepository.findByProviderUserId(connectionKey.getProviderUserId());
			UserConnection userConnection = null;
			if (user == null) {
				userConnection = userConnectionRepository.findOne(UserConnectionId.builder().userId(connectionKey.getProviderUserId()).providerId(connectionKey.getProviderId()).providerUserId(connectionKey.getProviderUserId()).build());
				user = userService.fbSignUp(userConnection);
			}
			else {
				userConnection = user.getUserConnection();
			}
			
			List<GrantedAuthority> listGrantedAuthority = new ArrayList<GrantedAuthority>();
			listGrantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
			if (user.getUsername().equals("OraOraOrraah")) {
				listGrantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), listGrantedAuthority);
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    
		    httpServletRequest.getSession().setAttribute(Consts.AUTHEN_USER, AuthUser.builder().userId(user.getUserId()).username(user.getUsername()).displayName(userConnection.getDisplayName()).imageURL(userConnection.getImageUrl()).build());
		}
		catch (Exception e) {
			log.error(" SignIn Error: " + e);
		}
		return null;
	}
	
}
