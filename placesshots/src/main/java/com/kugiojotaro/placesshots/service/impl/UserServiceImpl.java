package com.kugiojotaro.placesshots.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.UserChangePasswordDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.UserMapper;
import com.kugiojotaro.placesshots.repository.UserRepository;
import com.kugiojotaro.placesshots.service.UserService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Override
	public Boolean create(UserDto userDto) {
		log.debug(" create user: " + userDto.getUsername());
		
		try {
			User user = userMapper.toPersistenceBean(userDto);
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			user.setCreateDate(new Date());
			userRepository.save(user);
			
			log.info(" registered user: " + userDto.getUsername());
		}
		catch (Exception ex) {
			log.error(ex, ex);
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean update(UserDto userDto) {
		log.debug(" update");
		
		try {
			User user = userRepository.findByUsername(userDto.getUsername());
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			user = userMapper.toPersistenceBean(userDto);
			user.setUpdateDate(new Date());
			userRepository.save(user);
		}
		catch (Exception ex) {
			log.error(ex, ex);
			return false;
		}
		
		return true;
	}
	
	@Override
	public Boolean updateProfile(UserDto userDto) {
		log.debug(" update");
		
		try {
			User user = userRepository.findByUsername(userDto.getUsername());
			user.setUpdateDate(new Date());
			userRepository.save(user);
		}
		catch (Exception ex) {
			log.error(ex, ex);
			return false;
		}
		
		return true;
	}
	
	@Override
	public Boolean delete(String username) {
		log.debug(" delete");
		
		return true;
	}

	@Override
	public UserDto findByUsername(String username) {
		log.debug(" findByUsername (username: " + username + ")");
		
		UserDto userDto = null;
		
		try {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				userDto = userMapper.toDtoBean(user);
				if (user.getUserConnection() == null) {
					userDto.setDisplayName(userDto.getUsername());
				}
				else {
					userDto.setDisplayName(user.getUserConnection().getDisplayName());
					userDto.setImageURL(user.getUserConnection().getImageUrl());
				}
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return userDto;
	}
	
	@Override
	public UserDto findByUsernameAndPassword(String username, String password) {
		log.debug(" findByUsernameAndPassword, username: " + username);
		
		UserDto userDto = null;
		
		try {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				String md5Password = md5PasswordEncoder.encodePassword(password, null);
				if (user.getPassword().equals(md5Password)) {
					userDto = userMapper.toDtoBean(user);
				}
			}
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		
		return userDto;
	}

	@Override
	public Page<UserDto> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Boolean changepassword(UserChangePasswordDto userChangePasswordDto) {
		log.info(" changepassword (username: " + userChangePasswordDto.getUsername() + ")");
		try {
			User user = userRepository.findByUsername(userChangePasswordDto.getUsername());
			user.setPassword(md5PasswordEncoder.encodePassword(userChangePasswordDto.getPassword(), null));
			userRepository.save(user);
			log.info(" changepassword success");
			return true;
		}
		catch (Exception ex) {
			log.error(ex, ex);
			return false;
		}
	}
	
}