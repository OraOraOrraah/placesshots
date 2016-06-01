package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;


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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getFixtureId() {
		return fixtureId;
	}
	public void setFixtureId(String fixtureId) {
		this.fixtureId = fixtureId;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
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

}