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
@Table(name = "predict_champion")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PredictChampion implements Serializable {

	private static final long serialVersionUID = 4796189886156915108L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "team_id")
	private Integer teamId;
	
	@Column(name = "round")
	private String round;
	
	@Column(name = "create_date")
	private Date createDate;

}