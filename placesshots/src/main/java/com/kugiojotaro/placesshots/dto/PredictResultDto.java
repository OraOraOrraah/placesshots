package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PredictResultDto implements Serializable {

	private static final long serialVersionUID = -5654550503823522412L;
	
	private String username;
	private String userIcon;
	private List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
	private Integer point;
	private String displayPoint;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public List<PredictResultDetailDto> getListPredictResultDetailDto() {
		return listPredictResultDetailDto;
	}
	public void setListPredictResultDetailDto(List<PredictResultDetailDto> listPredictResultDetailDto) {
		this.listPredictResultDetailDto = listPredictResultDetailDto;
	}
	public String getDisplayPoint() {
		return displayPoint;
	}
	public void setDisplayPoint(String displayPoint) {
		this.displayPoint = displayPoint;
	}
	
}