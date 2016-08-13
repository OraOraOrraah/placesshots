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
public class FixtureDto implements Serializable {

	private static final long serialVersionUID = 9184433926813429663L;
	
	private String id;
	private String leagueId;
	private String week;
	private String homeId;
	private String homeTitle;
	private String homeShortTitle;
	private String awayId;
	private String awayTitle;
	private String awayShortTitle;
	private String homeScore;
	private String awayScore;
	private String homeExtraTimeScore;
	private String awayExtraTimeScore;
	private String homePenaltyScore;
	private String awayPenaltyScore;
	private String fixtureDate;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	
}