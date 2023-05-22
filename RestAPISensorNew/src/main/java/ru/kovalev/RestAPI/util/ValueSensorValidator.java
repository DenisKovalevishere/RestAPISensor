package ru.kovalev.RestAPI.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.kovalev.RestAPI.dto.ValueSensorDTO;
import ru.kovalev.RestAPI.services.SensorsService;


@Component
public class ValueSensorValidator implements Validator{

	private final SensorsService sensorsService;
	
	public ValueSensorValidator(SensorsService sensorsService) {
		this.sensorsService = sensorsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ValueSensorDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValueSensorDTO valueSensorDTO = (ValueSensorDTO) target;
		
		if(valueSensorDTO.getSensor() == null) {
			return;
		} 
		
		if(sensorsService.findByName(valueSensorDTO.getSensor().getName()).isEmpty()) {
			errors.reject("sensor","This sensor not found");
		}
		
	}

}
