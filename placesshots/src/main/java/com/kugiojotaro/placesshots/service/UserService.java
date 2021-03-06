
package com.kugiojotaro.placesshots.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.UserChangePasswordDto;
import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.entity.User;
import com.kugiojotaro.placesshots.entity.UserConnection;

public interface UserService {

	@Transactional
	public Boolean create(UserDto userDto);

	@Transactional
	public Boolean update(UserDto userDto);
	
	@Transactional
	public Boolean updateProfile(UserDto userDto);

	@Transactional
	public Boolean delete(String username);
	
	public UserDto findByUserId(Integer userId);
	
	public UserDto findByUsername(String username);
	
	public UserDto findByUsernameAndPassword(String username, String password);
	
	public Page<UserDto> findAll(Pageable pageable);
	
	@Transactional
	public Boolean changepassword(UserChangePasswordDto userChangePasswordDto);
	
	@Transactional
	public User fbSignUp(UserConnection userConnection);

}