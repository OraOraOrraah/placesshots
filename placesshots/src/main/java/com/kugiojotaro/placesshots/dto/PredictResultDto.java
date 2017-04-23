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
public class PredictResultDto implements Serializable {

	private static final long serialVersionUID = -5654550503823522412L;
	
	private String displayName;
	private List<PredictResultDetailDto> listPredictResultDetailDto = new ArrayList<PredictResultDetailDto>();
	private Integer point;
	private String displayPoint;
	
}