package com.kugiojotaro.placesshots.authen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.repository.UserRepository;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info(" authenticate: " + authentication.getName());
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (authorizedUser(username, password)) {
			List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
			listAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			if (username.equals("OraOraOrraah")) {
				listAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			Authentication auth = new UsernamePasswordAuthenticationToken(username, password, listAuthorities);
			return auth;
		}
		else {
			throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
		}
	}

	private boolean authorizedUser(String username, String password) {
		try {
			User user = userRepository.findByUsername(username);
			if (user.getUsername().equals(username) && bcryptPasswordEncoder.matches(password, user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception ex) {
			log.error(ex);
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}