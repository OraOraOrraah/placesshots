package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "predict")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Predict implements Serializable {
	
	private static final long serialVersionUID = 3835918845458351457L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "username")
	private String user;
	
	@Column(name = "week")
	private Short week;
	
	@Column(name = "fixture_id")
	private Long fixture;
	
	@Column(name = "home_score")
	private Short homeScore;
	
	@Column(name = "away_score")
	private Short awayScore;

	@Column(name = "point")
	private Short point;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_date")
	private Date updateDate;

}