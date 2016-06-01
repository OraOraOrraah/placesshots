package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class TeamDto implements Serializable {

	private static final long serialVersionUID = 875368361860214773L;
	
	private String id;
	private String title;
	private String shortTitle;
	private String leagueId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

}