package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getHomeId() {
		return homeId;
	}
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}
	public String getHomeTitle() {
		return homeTitle;
	}
	public void setHomeTitle(String homeTitle) {
		this.homeTitle = homeTitle;
	}
	public String getHomeShortTitle() {
		return homeShortTitle;
	}
	public void setHomeShortTitle(String homeShortTitle) {
		this.homeShortTitle = homeShortTitle;
	}
	public String getAwayId() {
		return awayId;
	}
	public void setAwayId(String awayId) {
		this.awayId = awayId;
	}
	public String getAwayTitle() {
		return awayTitle;
	}
	public void setAwayTitle(String awayTitle) {
		this.awayTitle = awayTitle;
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
	public String getFixtureDate() {
		return fixtureDate;
	}
	public void setFixtureDate(String fixtureDate) {
		this.fixtureDate = fixtureDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}