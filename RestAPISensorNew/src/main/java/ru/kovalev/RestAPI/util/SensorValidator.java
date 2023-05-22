package ru.kovalev.RestAPI.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.kovalev.RestAPI.dto.SensorDTO;
import ru.kovalev.RestAPI.services.SensorsService;



@Component
public class SensorValidator implements Validator{

	private final SensorsService sensorsService;
	
	public SensorValidator(SensorsService sensorsService) {
		this.sensorsService = sensorsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SensorDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SensorDTO sensorDTO = (SensorDTO) target;
		
		if(sensorsService.findByName(sensorDTO.getName()).isPresent()) {
			errors.rejectValue("name", "This name is already taken");
		}
	}



	
	
}
