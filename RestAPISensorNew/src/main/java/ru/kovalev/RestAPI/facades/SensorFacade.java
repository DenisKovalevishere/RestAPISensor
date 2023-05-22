package ru.kovalev.RestAPI.facades;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.kovalev.RestAPI.dto.SensorDTO;
import ru.kovalev.RestAPI.models.Sensor;
import ru.kovalev.RestAPI.services.SensorsService;

@Component
public class SensorFacade {

	private final SensorsService sensorsService;
	private final ModelMapper modelMapper;

	public SensorFacade(SensorsService sensorsService, ModelMapper modelMapper) {
		this.sensorsService = sensorsService;
		this.modelMapper = modelMapper;

	}
	
	
	private Sensor convertToSensor(SensorDTO sensorDTO) {
		return modelMapper.map(sensorDTO, Sensor.class);
	}
	
	private SensorDTO convertToSensorDTO(Sensor sensor) {
		return modelMapper.map(sensor, SensorDTO.class);
	}
	
	public List<SensorDTO> getSensorsDTO(){
		return sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
	}
	
	public SensorDTO getSensorDTOById(int id) {
		return convertToSensorDTO(sensorsService.findOne(id));
	}
	
	public void addSensorDTO(SensorDTO sensorDTO) {
		sensorsService.saveSensor(convertToSensor(sensorDTO));
	}
}
