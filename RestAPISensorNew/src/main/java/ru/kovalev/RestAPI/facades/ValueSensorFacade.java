package ru.kovalev.RestAPI.facades;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.kovalev.RestAPI.dto.ValueSensorDTO;
import ru.kovalev.RestAPI.models.ValueSensor;
import ru.kovalev.RestAPI.services.ValuesSensorService;

@Component
public class ValueSensorFacade {

	private final ValuesSensorService valuesSensorService;
	private final ModelMapper modelMapper;
	
	public ValueSensorFacade(ValuesSensorService valuesSensorService, ModelMapper modelMapper) {
		this.valuesSensorService = valuesSensorService;
		this.modelMapper = modelMapper;
	}
	
	private ValueSensor convertToValueSensor(ValueSensorDTO valueSensorDTO) {
		return modelMapper.map(valueSensorDTO, ValueSensor.class);
	}
	
	private ValueSensorDTO convertToValueSensorDTO(ValueSensor valueSensor) {
		return modelMapper.map(valueSensor, ValueSensorDTO.class);
	}
	
	public List<ValueSensorDTO> getValueSensorDTO(){
		return valuesSensorService.findAll().stream().map(this::convertToValueSensorDTO).collect(Collectors.toList());
	}
	
	public int getRainyDaysSensorDTO() {
		return valuesSensorService.countRainDays();
	}
	
	public void addValueSensorDTO(ValueSensorDTO valueSensorDTO) {
		valuesSensorService.saveValueSensor(convertToValueSensor(valueSensorDTO));
	}
	
	public ValueSensorDTO getValueSensorDTO(int id) {
		return convertToValueSensorDTO(valuesSensorService.findOne(id));
	}
	
}
