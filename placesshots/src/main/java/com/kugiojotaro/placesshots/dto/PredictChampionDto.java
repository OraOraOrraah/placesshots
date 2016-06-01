package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class PredictChampionDto implements Serializable {

	private static final long serialVersionUID = 236161925302832755L;
	
	private String id;
	private String user;
	private String teamId;
	private String round;
	
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
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	
}