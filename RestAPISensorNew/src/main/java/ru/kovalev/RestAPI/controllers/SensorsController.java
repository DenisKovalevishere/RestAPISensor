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

import ru.kovalev.RestAPI.dto.SensorDTO;
import ru.kovalev.RestAPI.facades.SensorFacade;
import ru.kovalev.RestAPI.util.SensorValidator;
import ru.kovalev.RestAPI.util.ValueSensorErrorResponse;
import ru.kovalev.RestAPI.util.ValueSensorException;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

	private final SensorValidator sensorValidator;
	private final SensorFacade sensorFacade;
	

	public SensorsController(SensorValidator sensorValidator, SensorFacade sensorFacade) {
		this.sensorValidator = sensorValidator;
		this.sensorFacade = sensorFacade;
	}
	
	@GetMapping()
	public List<SensorDTO> getSensors(){
		return sensorFacade.getSensorsDTO();
	}
	
	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {		
		sensorValidator.validate(sensorDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			returnErrorsToClient(bindingResult);
		}
		
		sensorFacade.addSensorDTO(sensorDTO);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public SensorDTO getSensorById(@PathVariable("id") int id) {
		return sensorFacade.getSensorDTOById(id);
	}
	
	@ExceptionHandler
	private ResponseEntity<ValueSensorErrorResponse> handleException(ValueSensorException e){
		ValueSensorErrorResponse response = new ValueSensorErrorResponse(
				e.getMessage(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
}
