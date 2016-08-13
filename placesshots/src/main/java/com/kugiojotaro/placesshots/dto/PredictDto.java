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
public class PredictDto implements Serializable {
	
	private static final long serialVersionUID = 8202071230105475721L;
	
	private String id;
	private String user;
	private String week;
	private String fixtureId;
	private String homeScore;
	private String awayScore;
	private String score;
	private String redCardFlag;
	private String overTimeFlag;
	private String penaltyFlag;

}