package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeagueDto implements Serializable {

	private static final long serialVersionUID = -220626232147360211L;

	private String id;
	private String title;
	
}