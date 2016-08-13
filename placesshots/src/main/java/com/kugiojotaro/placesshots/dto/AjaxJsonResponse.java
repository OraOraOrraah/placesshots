package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;
import java.util.Map;

import com.kugiojotaro.placesshots.util.Consts;

public class AjaxJsonResponse implements Serializable {

	private static final long serialVersionUID = -1797256341247377542L;
	
	private String result;
	private String returnValue;
	private Map<String,String> errorsMap;

	public AjaxJsonResponse() {
		setResult(Consts.RESULT_SUCCESS);
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}
	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

}