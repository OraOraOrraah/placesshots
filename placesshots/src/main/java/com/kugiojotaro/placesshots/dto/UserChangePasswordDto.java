package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserChangePasswordDto implements Serializable {
	
	private static final long serialVersionUID = -5188062645965331298L;

	private String username;
	
	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	private String passwordConfirm;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}