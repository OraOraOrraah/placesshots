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
public class PredictDisplayDto implements Serializable {

	private static final long serialVersionUID = -7459916448668559853L;
	
	private String no;
	private String fixtureId;
	private String homeTitle;
	private String homeShortTitle;
	private String awayTitle;
	private String awayShortTitle;
	private String homeScore;
	private String awayScore;
	private String redCardFlag;
	private String overTimeFlag;
	private String penaltyFlag;

}