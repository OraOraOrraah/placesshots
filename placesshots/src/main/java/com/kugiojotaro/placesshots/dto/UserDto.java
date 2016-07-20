package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.kugiojotaro.placesshots.entity.UserItem;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 4709463601376556555L;
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String username;
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String password;
	
	@NotBlank
	private String passwordConfirm;
	
	@Email
	private String email;
	
	private String firstname;
	private String lastname;
	private String icon;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	
	private Set<UserItem> userItems = new HashSet<UserItem>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Set<UserItem> getUserItems() {
		return userItems;
	}
	public void setUserItems(Set<UserItem> userItems) {
		this.userItems = userItems;
	}
	
}