package ru.kovalev.RestAPI.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.RestAPI.models.Sensor;
import ru.kovalev.RestAPI.models.ValueSensor;
import ru.kovalev.RestAPI.repositories.SensorsRepository;


@Service
@Transactional
public class SensorsService {

	private final SensorsRepository sensorRepository;

	public SensorsService(SensorsRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}
	
	public List<Sensor> findAll(){
		return sensorRepository.findAll();
	}
	
	public Sensor findOne(int id) {
		Optional <Sensor> foundSensor = sensorRepository.findById(id);
		return foundSensor.orElse(null); //SensorNotFoundExeption
	}
	
	public Optional<Sensor> findByName(String name) {
		return sensorRepository.findByName(name);
	}
	
	@Transactional
	public void saveSensor(Sensor sensor) {
		enrichSensor(sensor);
		
		sensorRepository.save(sensor); 
	}
	
	@Transactional
	public void updateSensor(Sensor updatedSensor, int id) {
		updatedSensor.setId(id);
		sensorRepository.save(updatedSensor);
	}
	
	@Transactional
	public void deleteSensor(int id) {
		sensorRepository.deleteById(id);
	}
	
	private void enrichSensor(Sensor sensor) {
		sensor.setCreatedAt(LocalDateTime.now());
	}
	
	public List<ValueSensor> getValueSensorBySensorId(int id){
		Optional <Sensor> sensor = sensorRepository.findById(id);
		
		if(sensor.isPresent()) {
			Hibernate.initialize(sensor.get().getValues());
			return sensor.get().getValues();
		} else {
			return Collections.emptyList();
		}
	}
	
}
