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
public class PredictResultDetailDto implements Serializable {
	
	private static final long serialVersionUID = 5727121384553886443L;
	
	private String no;
	private String homeTitle;
	private String awayTitle;
	private String homeShortTitle;
	private String awayShortTitle;
	private String homeScore;
	private String awayScore;
	private String homeExtraTimeScore;
	private String awayExtraTimeScore;
	private String homePenaltyScore;
	private String awayPenaltyScore;
	private String homeTotalScore;
	private String awayTotalScore;
	private String predictHomeScore;
	private String predictAwayScore;
	private String round;
	private Integer point;
	
}