package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

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
	private Integer point;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getHomeTitle() {
		return homeTitle;
	}
	public void setHomeTitle(String homeTitle) {
		this.homeTitle = homeTitle;
	}
	public String getAwayTitle() {
		return awayTitle;
	}
	public void setAwayTitle(String awayTitle) {
		this.awayTitle = awayTitle;
	}
	public String getHomeShortTitle() {
		return homeShortTitle;
	}
	public void setHomeShortTitle(String homeShortTitle) {
		this.homeShortTitle = homeShortTitle;
	}
	public String getAwayShortTitle() {
		return awayShortTitle;
	}
	public void setAwayShortTitle(String awayShortTitle) {
		this.awayShortTitle = awayShortTitle;
	}
	public String getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}
	public String getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(String awayScore) {
		this.awayScore = awayScore;
	}
	public String getHomeExtraTimeScore() {
		return homeExtraTimeScore;
	}
	public void setHomeExtraTimeScore(String homeExtraTimeScore) {
		this.homeExtraTimeScore = homeExtraTimeScore;
	}
	public String getAwayExtraTimeScore() {
		return awayExtraTimeScore;
	}
	public void setAwayExtraTimeScore(String awayExtraTimeScore) {
		this.awayExtraTimeScore = awayExtraTimeScore;
	}
	public String getHomePenaltyScore() {
		return homePenaltyScore;
	}
	public void setHomePenaltyScore(String homePenaltyScore) {
		this.homePenaltyScore = homePenaltyScore;
	}
	public String getAwayPenaltyScore() {
		return awayPenaltyScore;
	}
	public void setAwayPenaltyScore(String awayPenaltyScore) {
		this.awayPenaltyScore = awayPenaltyScore;
	}
	public String getHomeTotalScore() {
		return homeTotalScore;
	}
	public void setHomeTotalScore(String homeTotalScore) {
		this.homeTotalScore = homeTotalScore;
	}
	public String getAwayTotalScore() {
		return awayTotalScore;
	}
	public void setAwayTotalScore(String awayTotalScore) {
		this.awayTotalScore = awayTotalScore;
	}
	public String getPredictHomeScore() {
		return predictHomeScore;
	}
	public void setPredictHomeScore(String predictHomeScore) {
		this.predictHomeScore = predictHomeScore;
	}
	public String getPredictAwayScore() {
		return predictAwayScore;
	}
	public void setPredictAwayScore(String predictAwayScore) {
		this.predictAwayScore = predictAwayScore;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	
}