package ru.kovalev.restAssured.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class Sensor {
	

	private int id;

	private String name;
	

	private LocalDateTime createdAt;

	private List<ValueSensor> values;

	public Sensor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<ValueSensor> getValues() {
		return values;
	}

	public void setValues(List<ValueSensor> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", createdAt=" + createdAt + "]";
	}
	
	
}
