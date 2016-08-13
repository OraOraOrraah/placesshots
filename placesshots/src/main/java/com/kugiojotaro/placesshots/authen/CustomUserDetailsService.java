package com.kugiojotaro.placesshots.authen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.repository.UserRepository;

import lombok.extern.log4j.Log4j;

@Transactional(readOnly = true)
@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		log.info(" loadUserByUsername: " + username);
		
		UserDetails userDetails = null;

		try {
			User user = userRepository.findByUsername(username);
			
			if (!username.equals(user.getUsername())) {
				log.info(" user in table: " + user.getUsername());
				throw new UsernameNotFoundException("Error in retrieving user");
			}

			List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
	        //for (String role : roles) {
	            listOfAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	        //}
	            
	        if (username.equals("OraOraOrraah")) {
	        	listOfAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        }
	        
	        userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().toLowerCase(), true, true, true, true, listOfAuthorities);

		} catch (Exception e) {
			log.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return userDetails;
	}
	
}