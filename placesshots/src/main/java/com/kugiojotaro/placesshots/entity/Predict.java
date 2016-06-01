package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "predict")
public class Predict implements Serializable {
	
	private static final long serialVersionUID = 3835918845458351457L;

	private Long id;
	private String user;
	private Short week;
	private Long fixture;
	private Short homeScore;
	private Short awayScore;
	private Short redCardFlag;
	private Short overTimeFlag;
	private Short penaltyFlag;
	private Short point;
	private Date createDate;
	private Date updateDate;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username")
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
    
    @Column(name = "week")
	public Short getWeek() {
		return week;
    }
	public void setWeek(Short week) {
		this.week = week;
	}
	
	@Column(name = "fixture_id")
	public Long getFixture() {
		return fixture;
	}
    public void setFixture(Long fixture) {
		this.fixture = fixture;
	}

    @Column(name = "home_score")
	public Short getHomeScore() {
		return homeScore;
	}
    public void setHomeScore(Short homeScore) {
		this.homeScore = homeScore;
	}
	
	@Column(name = "away_score")
	public Short getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(Short awayScore) {
		this.awayScore = awayScore;
	}
	
	@Column(name = "red_card_flag")
	public Short getRedCardFlag() {
		return redCardFlag;
	}
	public void setRedCardFlag(Short redCardFlag) {
		this.redCardFlag = redCardFlag;
	}
	
	@Column(name = "over_time_flag")
	public Short getOverTimeFlag() {
		return overTimeFlag;
	}
	public void setOverTimeFlag(Short overTimeFlag) {
		this.overTimeFlag = overTimeFlag;
	}
	
	@Column(name = "penalty_flag")
	public Short getPenaltyFlag() {
		return penaltyFlag;
	}
	public void setPenaltyFlag(Short penaltyFlag) {
		this.penaltyFlag = penaltyFlag;
	}
	
	@Column(name = "point")
	public Short getPoint() {
		return point;
	}
	public void setPoint(Short point) {
		this.point = point;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}