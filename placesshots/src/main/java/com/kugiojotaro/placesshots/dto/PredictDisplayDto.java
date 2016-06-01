package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

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
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getFixtureId() {
		return fixtureId;
	}
	public void setFixtureId(String fixtureId) {
		this.fixtureId = fixtureId;
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
	public String getRedCardFlag() {
		return redCardFlag;
	}
	public void setRedCardFlag(String redCardFlag) {
		this.redCardFlag = redCardFlag;
	}
	public String getOverTimeFlag() {
		return overTimeFlag;
	}
	public void setOverTimeFlag(String overTimeFlag) {
		this.overTimeFlag = overTimeFlag;
	}
	public String getPenaltyFlag() {
		return penaltyFlag;
	}
	public void setPenaltyFlag(String penaltyFlag) {
		this.penaltyFlag = penaltyFlag;
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

}