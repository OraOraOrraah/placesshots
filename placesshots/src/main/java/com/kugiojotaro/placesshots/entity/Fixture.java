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

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "league_id")
	private Short league;
	
	@Column(name = "week")
	private Short week;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "home_id")
	private Team home;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "away_id")
	private Team away;
	
	@Column(name = "home_score")
	private Short homeScore;
	
	@Column(name = "away_score")
	private Short awayScore;
	
	@Column(name = "home_extra_time_score")
	private Short homeExtraTimeScore;
	
	@Column(name = "away_extra_time_score")
	private Short awayExtraTimeScore;
	
	@Column(name = "home_penalty_score")
	private Short homePenaltyScore;
	
	@Column(name = "away_penalty_score")
	private Short awayPenaltyScore;
	
	@Column(name = "fixture_date")
	private Date fixtureDate;

	@Column(name = "round")
	private String round;
	
	@Column(name = "create_by")
	private Integer createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_by")
	private Integer updateBy;
	
	@Column(name = "update_date")
	private Date updateDate;

}