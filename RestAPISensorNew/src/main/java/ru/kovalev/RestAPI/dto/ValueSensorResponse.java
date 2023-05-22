package ru.kovalev.RestAPI.dto;

import java.util.List;

public class ValueSensorResponse {

	private List<ValueSensorDTO> values;

	public ValueSensorResponse(List<ValueSensorDTO> values) {
		this.values = values;
	}

	public List<ValueSensorDTO> getValues() {
		return values;
	}

	public void setValues(List<ValueSensorDTO> values) {
		this.values = values;
	}
	
	
	
}
