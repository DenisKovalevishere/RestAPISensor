package ru.kovalev.RestAPI.controllers;


import static ru.kovalev.RestAPI.util.ErrorsUtil.returnErrorsToClient;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.kovalev.RestAPI.dto.ValueSensorDTO;
import ru.kovalev.RestAPI.facades.ValueSensorFacade;
import ru.kovalev.RestAPI.util.ValueSensorErrorResponse;
import ru.kovalev.RestAPI.util.ValueSensorException;
import ru.kovalev.RestAPI.util.ValueSensorValidator;

@RestController
@RequestMapping("/measurements")
public class ValuesSensorController {

	private final ValueSensorFacade valueSensorFacade;
	private final ValueSensorValidator valueSensorValidator;

	public ValuesSensorController(ValueSensorValidator valueSensorValidator, ValueSensorFacade valueSensorFacade) {
		this.valueSensorFacade = valueSensorFacade;
		this.valueSensorValidator = valueSensorValidator;
	}
	
	@GetMapping()
	public List<ValueSensorDTO> getValuesSensor(){
		return valueSensorFacade.getValueSensorDTO();
	}
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addValueSensor(@RequestBody @Valid ValueSensorDTO valueSensorDTO, BindingResult bindingResult) {
		
		valueSensorValidator.validate(valueSensorDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			returnErrorsToClient(bindingResult);
		}
		
		valueSensorFacade.addValueSensorDTO(valueSensorDTO);
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	
	@GetMapping("/rainyDaysCount")
	public int getRainyDays() {
		return valueSensorFacade.getRainyDaysSensorDTO();
	}
	
	@ExceptionHandler
	private ResponseEntity<ValueSensorErrorResponse> handleException(ValueSensorException e){
		ValueSensorErrorResponse response = new ValueSensorErrorResponse(
				e.getMessage(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public ValueSensorDTO getValueSensorById(@PathVariable("id") int id) {
		return valueSensorFacade.getValueSensorDTO(id);
	}
	
	
	
}
