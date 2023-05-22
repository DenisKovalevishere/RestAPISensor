package ru.kovalev.restAssured.models;

import java.time.LocalDateTime;
import java.util.Objects;


public class ValueSensor {


	private Integer id;
	

	private Double temp;
	

	private Boolean raining;
	

	private LocalDateTime createdAt;
	

	private Sensor sensor;


	public ValueSensor() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Boolean getRaining() {
		return raining;
	}

	public void setRaining(Boolean raining) {
		this.raining = raining;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, raining, temp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueSensor other = (ValueSensor) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && raining == other.raining
				&& temp == other.temp;
	}

	@Override
	public String toString() {
		return "ValueSensor [id=" + id + ", temp=" + temp + ", raining=" + raining + ", createdAt=" + createdAt + "]";
	}
	
	
}
