package com.kugiojotaro.placesshots.authen;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dao.UserDao;
import com.kugiojotaro.placesshots.entity.User;

@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	protected static Logger LOGGER = Logger.getLogger("CustomUserDetailsService");

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		LOGGER.info(" loadUserByUsername: " + username);
		
		UserDetails userDetails = null;

		try {
			User user = userDao.findOne(username);

			List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
	        //for (String role : roles) {
	            listOfAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
	        //}
	            
	        if (username.equals("OraOraOrraah")) {
	        	listOfAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
	        }
	        
	        userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().toLowerCase(), true, true, true, true, listOfAuthorities);

		} catch (Exception e) {
			LOGGER.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return userDetails;
	}
	
}