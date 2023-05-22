package ru.kovalev.RestAPI.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;





public class ValueSensorDTO {

	@Min(value=-100, message="Minimal temp should be -100")
	@Max(value=100, message = "Maximal temp should be 100")
	@NotNull
	private Double temp;
	
	@NotNull
	private Boolean raining;
	
	@NotNull
	private SensorDTO sensor;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(Boolean raining) {
		this.raining = raining;
	}

	public SensorDTO getSensor() {
		return sensor;
	}

	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}
	
	
}
