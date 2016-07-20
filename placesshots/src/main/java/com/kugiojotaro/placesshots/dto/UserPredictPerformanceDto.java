package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPredictPerformanceDto implements Serializable {
	
	private static final long serialVersionUID = 6100327101886435611L;
	
	private String week;
	private String day;
	private List<PredictResultDetailDto> listFixture = new ArrayList<PredictResultDetailDto>();
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<PredictResultDetailDto> getListFixture() {
		return listFixture;
	}
	public void setListFixture(List<PredictResultDetailDto> listFixture) {
		this.listFixture = listFixture;
	}
		
}