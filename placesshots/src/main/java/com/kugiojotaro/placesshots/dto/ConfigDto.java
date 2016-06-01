package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class ConfigDto implements Serializable {

	private static final long serialVersionUID = 7595960236758853992L;
	
	private String id;
	private String week;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	
}