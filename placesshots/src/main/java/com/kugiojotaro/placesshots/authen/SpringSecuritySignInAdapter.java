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
import com.kugiojotaro.placesshots.repository.UserCustomRepository;
import com.kugiojotaro.placesshots.util.Consts;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SpringSecuritySignInAdapter implements SignInAdapter {

	@Autowired
	private UserCustomRepository userCustomRepository;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest nativeWebRequest) {
		log.info(" signIn: ");
		
		ConnectionKey connectionKey = connection.getKey();
		String providerUserId = connectionKey.getProviderUserId();
		log.info(" providerUserId: " + providerUserId);
		
		User user = userCustomRepository.findByProviderUserId(providerUserId);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		log.info("Grant ROLE_USER to this user");
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (user.getUsername().equals("OraOraOrraah")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
	    
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	    httpServletRequest.getSession().setAttribute(Consts.AUTHEN_USER, AuthUser.builder().userId(user.getUserId()).username(user.getUsername()).displayName(user.getUserConnection() == null ? user.getUsername() : user.getUserConnection().getDisplayName()).imageURL(user.getUserConnection() == null ? "" : user.getUserConnection().getImageUrl()).build());
	    
		return null;
	}
	
}