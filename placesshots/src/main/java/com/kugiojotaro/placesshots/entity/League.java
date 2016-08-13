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
@Table(name = "league")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class League implements Serializable {

	private static final long serialVersionUID = 7930607112099369012L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Short id;
	
	@Column(name = "title")
    private String title;

}