package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 4709463601376556555L;
	
	private String userId;
	
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
	private String displayName;
	private String imageURL;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	
}