package ru.kovalev.RestAPI.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Table(name="sensor_value")
@Entity
public class ValueSensor {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="temp")
	@Min(value=-100, message="Minimal temp should be -100")
	@Max(value=100, message = "Maximal temp should be 100")
	@NotNull
	private Double temp;
	
	@Column(name="raining")
	@NotNull
	private Boolean raining;
	
	@Column(name="created_at")
	@NotNull
	private LocalDateTime createdAt;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="sensor_id", referencedColumnName = "id")
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
