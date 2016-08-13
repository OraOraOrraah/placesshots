package com.kugiojotaro.placesshots.entity;

import java.io.Serializable;

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
@Table(name = "team")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team implements Serializable {
	
	private static final long serialVersionUID = -8077703486821124467L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "title", length = 50)
    private String title;
	
	@Column(name = "short_title", length = 3)
    private String shortTitle;
	
	@Column(name = "league_id")
    private Short league;
	
}