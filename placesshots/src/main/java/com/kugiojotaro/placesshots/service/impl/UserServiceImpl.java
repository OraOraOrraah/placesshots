package com.kugiojotaro.placesshots.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dao.UserDao;
import com.kugiojotaro.placesshots.dto.UserChangePasswordDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.UserMapper;
import com.kugiojotaro.placesshots.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Override
	public Boolean create(UserDto userDto) {
		LOGGER.debug(" create user: " + userDto.getUsername());
		
		try {
			User user = userMapper.toPersistenceBean(userDto);
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			user.setCreateDate(new Date());
			userDao.save(user);
			
			LOGGER.info(" registered user: " + userDto.getUsername());
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean update(UserDto userDto) {
		LOGGER.debug(" update");
		
		try {
			User user = userDao.findByUsername(userDto.getUsername());
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			user = userMapper.toPersistenceBean(userDto);
			user.setUpdateDate(new Date());
			userDao.save(user);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
		
		return true;
	}
	
	@Override
	public Boolean updateProfile(UserDto userDto) {
		LOGGER.debug(" update");
		
		try {
			User user = userDao.findByUsername(userDto.getUsername());
			user.setUpdateDate(new Date());
			userDao.save(user);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
		
		return true;
	}
	
	@Override
	public Boolean delete(String username) {
		LOGGER.debug(" delete");
		
		return true;
	}

	@Override
	public UserDto findByUsername(String username) {
		LOGGER.debug(" findByUsername (username: " + username + ")");
		
		UserDto userDto = null;
		
		try {
			User user = userDao.findByUsername(username);
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
			LOGGER.error(ex, ex);
		}
		
		return userDto;
	}
	
	@Override
	public UserDto findByUsernameAndPassword(String username, String password) {
		LOGGER.debug(" findByUsernameAndPassword, username: " + username);
		
		UserDto userDto = null;
		
		try {
			User user = userDao.findByUsername(username);
			if (user != null) {
				String md5Password = md5PasswordEncoder.encodePassword(password, null);
				if (user.getPassword().equals(md5Password)) {
					userDto = userMapper.toDtoBean(user);
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return userDto;
	}

	@Override
	public Page<UserDto> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Boolean changepassword(UserChangePasswordDto userChangePasswordDto) {
		LOGGER.info(" changepassword (username: " + userChangePasswordDto.getUsername() + ")");
		try {
			User user = userDao.findByUsername(userChangePasswordDto.getUsername());
			user.setPassword(md5PasswordEncoder.encodePassword(userChangePasswordDto.getPassword(), null));
			userDao.save(user);
			LOGGER.info(" changepassword success");
			return true;
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
	}
	
}