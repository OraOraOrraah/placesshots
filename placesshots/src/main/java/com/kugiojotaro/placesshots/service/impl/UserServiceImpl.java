package com.kugiojotaro.placesshots.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kugiojotaro.placesshots.dto.UserChangePasswordDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.entity.UserConnection;
import com.kugiojotaro.placesshots.mapper.UserMapper;
import com.kugiojotaro.placesshots.repository.UserCustomRepository;
import com.kugiojotaro.placesshots.repository.UserRepository;
import com.kugiojotaro.placesshots.service.UserService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCustomRepository userCustomRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public Boolean create(UserDto userDto) {
		log.debug(" create user: " + userDto.getUsername());
		
		try {
			User user = userMapper.toPersistenceBean(userDto);
			user.setPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
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
			user.setPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
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
	public UserDto findByUserId(Integer userId) {
		log.debug(" findByUserId (userId: " + userId + ")");
		
		UserDto userDto = null;
		
		try {
			User user = userRepository.findOne(userId);
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
				if (bcryptPasswordEncoder.matches(user.getPassword(), bcryptPasswordEncoder.encode(password))) {
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
		log.info(" changepassword (userId: " + userChangePasswordDto.getUserId() + ")");
		try {
			User user = userRepository.findOne(userChangePasswordDto.getUserId());
			user.setPassword(bcryptPasswordEncoder.encode(userChangePasswordDto.getPassword()));
			userRepository.save(user);
			log.info(" changepassword success");
			return true;
		}
		catch (Exception ex) {
			log.error(ex, ex);
			return false;
		}
	}

	@Override
	public User fbSignUp(UserConnection userConnection) {
		log.info(" fbSignUp");
		User user = null;
		try {
			user = userRepository.save(User.builder().username("").createDate(new Date()).build());
			userCustomRepository.joinUserConnection(user.getUserId(), userConnection);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
		return user;
	}
	
}