package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fixture")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fixture implements Serializable {

	private static final long serialVersionUID = -8142639886988638315L;

	private Long id;
	private Short league;
	private Short week;
	private Team home;
	private Team away;
	@JsonView(DataTablesOutput.View.class)
	private Short homeScore;
	@JsonView(DataTablesOutput.View.class)
	private Short awayScore;
	private Short homeExtraTimeScore;
	private Short awayExtraTimeScore;
	private Short homePenaltyScore;
	private Short awayPenaltyScore;
	private Date fixtureDate;
	private Short redCardFlag;
	private Short overTimeFlag;
	private Short penaltyFlag;
	private String round;
	private String createBy;
	private Date createDate;
	private String updateBy;
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
	
	@Column(name = "league_id")
	public Short getLeague() {
		return league;
	}
	public void setLeague(Short league) {
		this.league = league;
	}
	
	@Column(name = "week")
	public Short getWeek() {
		return week;
	}

	public void setWeek(Short week) {
		this.week = week;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "home_id")
	public Team getHome() {
		return home;
	}
	public void setHome(Team home) {
		this.home = home;
	}
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "away_id")
	public Team getAway() {
		return away;
	}
	public void setAway(Team away) {
		this.away = away;
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
	
	@Column(name = "home_extra_time_score")
	public Short getHomeExtraTimeScore() {
		return homeExtraTimeScore;
	}
	public void setHomeExtraTimeScore(Short homeExtraTimeScore) {
		this.homeExtraTimeScore = homeExtraTimeScore;
	}
	
	@Column(name = "away_extra_time_score")
	public Short getAwayExtraTimeScore() {
		return awayExtraTimeScore;
	}
	public void setAwayExtraTimeScore(Short awayExtraTimeScore) {
		this.awayExtraTimeScore = awayExtraTimeScore;
	}
	
	@Column(name = "home_penalty_score")
	public Short getHomePenaltyScore() {
		return homePenaltyScore;
	}
	public void setHomePenaltyScore(Short homePenaltyScore) {
		this.homePenaltyScore = homePenaltyScore;
	}
	
	@Column(name = "away_penalty_score")
	public Short getAwayPenaltyScore() {
		return awayPenaltyScore;
	}
	public void setAwayPenaltyScore(Short awayPenaltyScore) {
		this.awayPenaltyScore = awayPenaltyScore;
	}
	
	@Column(name = "fixture_date")
	public Date getFixtureDate() {
		return fixtureDate;
	}
	public void setFixtureDate(Date fixtureDate) {
		this.fixtureDate = fixtureDate;
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
	
	@Column(name = "round")
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	
	@Column(name = "create_by")
    public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}