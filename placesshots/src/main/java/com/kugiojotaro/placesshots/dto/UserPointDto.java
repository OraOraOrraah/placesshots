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
public class UserPointDto implements Serializable {
	
	private static final long serialVersionUID = 2823300521983690230L;
	
	private String rank;
	private String username;
	private Integer point;
	private Integer predictCount;
	private Integer correctResult;
	private Integer correctResultAndScore;
	private Integer incorrectResult;
	private Integer extraPoint;
	private Integer totalPoint;
		
}