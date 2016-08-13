package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
}