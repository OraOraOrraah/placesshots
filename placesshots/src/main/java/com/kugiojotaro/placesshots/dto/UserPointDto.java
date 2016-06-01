package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class UserPointDto implements Serializable {
	
	private static final long serialVersionUID = 2823300521983690230L;
	
	private String rank;
	private String username;
	private String userIcon;
	private Integer point;
	private Integer predictCount;
	private Integer correctResult;
	private Integer correctResultAndScore;
	private Integer incorrectResult;
	private Integer extraPoint;
	private Integer totalPoint;
	private String extraPointFlag;
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
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
	public Integer getPredictCount() {
		return predictCount;
	}
	public void setPredictCount(Integer predictCount) {
		this.predictCount = predictCount;
	}
	public Integer getCorrectResult() {
		return correctResult;
	}
	public void setCorrectResult(Integer correctResult) {
		this.correctResult = correctResult;
	}
	public Integer getCorrectResultAndScore() {
		return correctResultAndScore;
	}
	public void setCorrectResultAndScore(Integer correctResultAndScore) {
		this.correctResultAndScore = correctResultAndScore;
	}
	public Integer getIncorrectResult() {
		return incorrectResult;
	}
	public void setIncorrectResult(Integer incorrectResult) {
		this.incorrectResult = incorrectResult;
	}
	public Integer getExtraPoint() {
		return extraPoint;
	}
	public void setExtraPoint(Integer extraPoint) {
		this.extraPoint = extraPoint;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public String getExtraPointFlag() {
		return extraPointFlag;
	}
	public void setExtraPointFlag(String extraPointFlag) {
		this.extraPointFlag = extraPointFlag;
	}
		
}