package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class PredictChampionDisplayDto implements Serializable {

	private static final long serialVersionUID = 5712341377323399756L;
	
	private String id;
	private String user;
	private String teamId;
	private String teamTitle;
	private String teamShortTitle;
	private String round;
	private String point;
	
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
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamTitle() {
		return teamTitle;
	}
	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	public String getTeamShortTitle() {
		return teamShortTitle;
	}
	public void setTeamShortTitle(String teamShortTitle) {
		this.teamShortTitle = teamShortTitle;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}

}