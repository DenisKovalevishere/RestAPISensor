package ru.kovalev.RestAPI.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.RestAPI.models.Sensor;
import ru.kovalev.RestAPI.models.ValueSensor;
import ru.kovalev.RestAPI.repositories.ValuesSensorRepository;

@Service
@Transactional
public class ValuesSensorService {

	private final ValuesSensorRepository valueSensorRepository;
	private final SensorsService sensorsService;

	public ValuesSensorService(ValuesSensorRepository valueSensorRepository, SensorsService sensorsService) {
		this.valueSensorRepository = valueSensorRepository;
		this.sensorsService = sensorsService;
	}
	
	public List<ValueSensor> findAll(){
		return valueSensorRepository.findAll();
	}
	
	public ValueSensor findOne(int id) {
		Optional <ValueSensor> foundSensor = valueSensorRepository.findById(id);
		return foundSensor.orElse(null); //ValueSensorNotFoundException
	}
	
	public int countRainDays() {
		List<ValueSensor> values = valueSensorRepository.findByRaining(true);
		List<LocalDate> date = new ArrayList<>();
		for(ValueSensor value: values) {
			date.add(value.getCreatedAt().toLocalDate());
		}
		return date.stream().distinct().toList().size();
	}
	
	
	@Transactional
	public void saveValueSensor(ValueSensor valueSensor) {
		enrichValueSensor(valueSensor);
		
		valueSensorRepository.save(valueSensor);
	}
	
	@Transactional
	public void deleteValueSensor(int id) {
		valueSensorRepository.deleteById(id);
	}
	
	private void enrichValueSensor(ValueSensor valueSensor) {
		valueSensor.setCreatedAt(LocalDateTime.now());
		valueSensor.setSensor(sensorsService.findByName(valueSensor.getSensor().getName()).get());
	}
	
	public Sensor findByValueSensorId(int id) {
		return valueSensorRepository.findById(id).map(ValueSensor::getSensor).orElse(null);
	}
	
}
