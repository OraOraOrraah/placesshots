package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUser implements Serializable {
	
	private static final long serialVersionUID = -4380504794451241570L;
	
	private Integer userId;
	private String username;
	private String displayName;
	private String imageURL;
	
//	public UserDetail(String username, String displayName, String imageURL) {
//		this.username = username;
//		this.displayName = displayName;
//		this.imageURL = imageURL;
//	}
	
}