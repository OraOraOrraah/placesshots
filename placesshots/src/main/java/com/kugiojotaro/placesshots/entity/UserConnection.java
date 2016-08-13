package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userconnection")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserConnection implements Serializable {
	
	private static final long serialVersionUID = 3675182636298982921L;
	
	@EmbeddedId
	private UserConnectionId id;
	
	@Column(name = "rank")
	private Integer rank;
	
	@Column(name = "displayName")
	private String displayName;
	
	@Column(name = "profileUrl")
	private String profileUrl;
	
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@Column(name = "accessToken")
	private String accessToken;
	
	@Column(name = "secret")
	private String secret;
	
	@Column(name = "refreshToken")
	private String refreshToken;
	
	@Column(name = "expireTime")
	private Long expireTime;
	
}