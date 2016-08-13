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
public class PredictChampionDto implements Serializable {

	private static final long serialVersionUID = 236161925302832755L;
	
	private String id;
	private String user;
	private String teamId;
	private String round;
	
}