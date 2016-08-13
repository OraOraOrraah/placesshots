package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(of={"id", "shortTitle"})
public class TeamDto implements Serializable {

	private static final long serialVersionUID = 875368361860214773L;
	
	private String id;
	private String title;
	private String shortTitle;
	private String leagueId;

}