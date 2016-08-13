package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPredictPerformanceDto implements Serializable {
	
	private static final long serialVersionUID = 6100327101886435611L;
	
	private String week;
	private String day;
	private List<PredictResultDetailDto> listFixture = new ArrayList<PredictResultDetailDto>();
		
}