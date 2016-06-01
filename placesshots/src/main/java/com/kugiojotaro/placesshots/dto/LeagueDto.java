package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class LeagueDto implements Serializable {

	private static final long serialVersionUID = -220626232147360211L;

	private String id;
	private String title;
	
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
	
}