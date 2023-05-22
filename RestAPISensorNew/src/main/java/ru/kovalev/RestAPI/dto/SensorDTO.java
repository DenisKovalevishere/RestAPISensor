package ru.kovalev.RestAPI.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

	
	@Size(min=3, max=100, message = "Name should be between 2 and 100 characters")
	@NotEmpty(message="Name should not be empty")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
