package ru.kovalev.RestAPI.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name="sensor")
@Entity
public class Sensor {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	@Size(min=3, max=100, message = "Name should be between 2 and 100 characters")
	@NotEmpty(message="Name should not be empty")
	private String name;
	
	@Column(name="created_at")
	@NotNull
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY) 
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
