package com.kugiojotaro.placesshots.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dao.UserDao;
import com.kugiojotaro.placesshots.dao.UserItemDao;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.dto.UserItemDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.mapper.UserMapper;
import com.kugiojotaro.placesshots.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserItemDao userItemDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Override
	public Boolean create(UserDto userDto) {
		LOGGER.info(" create user: " + userDto.getUsername());
		
		try {
			User user = userMapper.toPersistenceBean(userDto);
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			user.setCreateDate(new Date());
			userDao.save(user);
			
			/*
			Item item2 = new Item();
			item2.setId((short) 1);
			
			Item item3 = new Item();
			item3.setId((short) 2);
			
			UserItem userItem = new UserItem();
			userItem.setItem(item2);
			userItem.setUser(user);
			userItem.setQuantity((short) 8);
			
			UserItem userItem2 = new UserItem();
			userItem2.setItem(item3);
			userItem2.setUser(user);
			userItem2.setQuantity((short) 2);
			
			userItemDao.save(userItem);
			userItemDao.save(userItem2);
			*/
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean update(UserDto userDto) {
		LOGGER.info(" update");
		
		try {
			User user = userDao.findOne(userDto.getUsername());
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
		LOGGER.info(" update");
		
		try {
			User user = userDao.findOne(userDto.getUsername());
			user.setIcon(userDto.getIcon());
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
		LOGGER.info(" delete");
		
		return true;
	}

	@Override
	public UserDto findByUsername(String username) {
		LOGGER.info(" findByUsername (username: " + username + ")");
		
		UserDto userDto = null;
		
		try {
			User user = userDao.findOne(username);
			if (user != null) {
				userDto = userMapper.toDtoBean(user);
			}
			
			LOGGER.info(" success");
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
	public List<UserItemDto> selectUserItem(String username) {
		LOGGER.info(" selectUserItem (username: " + username + ")");
		
		List<UserItemDto> result = new ArrayList<UserItemDto>();
		
		try {
			User user = userDao.findFetchItem(username);
			/*
			for (UserItem userItem : user.getUserItems()) {
				UserItemDto userItemDto = new UserItemDto();
				//userItemDto.setUsername(username);
				userItemDto.setItemTitle(userItem.getItem().getTitle());
				userItemDto.setQuantity(userItem.getQuantity());
				
				result.add(userItemDto);
			}
			*/
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
		}
		
		return result;
	}

	@Override
	public Boolean changepassword(UserDto userDto) {
		LOGGER.info(" changepassword (username: " + userDto.getUsername() + ")");
		
		try {
			User user = userDao.findOne(userDto.getUsername());
			user.setPassword(md5PasswordEncoder.encodePassword(userDto.getPassword(), null));
			userDao.save(user);
		}
		catch (Exception ex) {
			LOGGER.error(ex, ex);
			return false;
		}
		
		return true;
	}
	
}