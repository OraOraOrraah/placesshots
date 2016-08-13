package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserConnectionId implements Serializable {

	private static final long serialVersionUID = -3858799233809205095L;
	
	@Column(name = "userId", nullable = false)
	private String userId;
	
	@Column(name = "providerId", nullable = false)
	private String providerId;
	
	@Column(name = "providerUserId", nullable = false)
	private String providerUserId;
	
}