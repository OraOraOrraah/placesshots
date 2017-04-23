package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FixtureDataTablesDto implements Serializable {
	
	private static final long serialVersionUID = -1213649028628483303L;

	@JsonView(DataTablesOutput.View.class)
	private String homeTitle;
	
	@JsonView(DataTablesOutput.View.class)
	private String homeShortTitle;
	
	@JsonView(DataTablesOutput.View.class)
	private String awayTitle;
	
	@JsonView(DataTablesOutput.View.class)
	private String awayShortTitle;
	
	@JsonView(DataTablesOutput.View.class)
	private String homeScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String awayScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String homeExtraTimeScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String awayExtraTimeScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String homePenaltyScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String awayPenaltyScore;
	
	@JsonView(DataTablesOutput.View.class)
	private String fixtureDate;
	
	@JsonView(DataTablesOutput.View.class)
	private String round;
	
}