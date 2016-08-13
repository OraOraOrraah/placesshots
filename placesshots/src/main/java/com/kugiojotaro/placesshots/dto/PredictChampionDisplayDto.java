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
public class PredictChampionDisplayDto implements Serializable {

	private static final long serialVersionUID = 5712341377323399756L;
	
	private String id;
	private String user;
	private String teamId;
	private String teamTitle;
	private String teamShortTitle;
	private String round;
	private String point;

}