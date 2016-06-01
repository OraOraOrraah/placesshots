
package com.kugiojotaro.placesshots.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.kugiojotaro.placesshots.dto.UserDto;
import com.kugiojotaro.placesshots.dto.UserItemDto;

public interface UserService {

	@Transactional
	public Boolean create(UserDto userDto);

	@Transactional
	public Boolean update(UserDto userDto);
	
	@Transactional
	public Boolean updateProfile(UserDto userDto);

	@Transactional
	public Boolean delete(String username);

	public UserDto findByUsername(String username);
	
	public Page<UserDto> findAll(Pageable pageable);
	
	public List<UserItemDto> selectUserItem(String username);
	
	@Transactional
	public Boolean changepassword(UserDto userDto);

}