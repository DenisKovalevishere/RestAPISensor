package ru.kovalev.RestAPI.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import ru.kovalev.RestAPI.models.Sensor;
import ru.kovalev.RestAPI.models.ValueSensor;
import ru.kovalev.RestAPI.repositories.ValuesSensorRepository;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ValuesSensorServiceTest {

	private String nameSensor = "nameSensor";

	@Autowired
	private ValuesSensorService valuesSensorService;
	
	@Autowired
	private SensorsService sensorsService;
	
	@MockBean
	private ValuesSensorRepository valuesSensorRepository;
	

	
	
	
	private Sensor createSensor() {
		if(sensorsService.findByName(nameSensor).isPresent()) {
			deleteSensor();
		}
		Sensor sensor = new Sensor();
		sensor.setName(nameSensor);
		sensorsService.saveSensor(sensor);
		return sensor;
	}
	
	private ValueSensor createValueSensor() {
		ValueSensor value = new ValueSensor();
		value.setSensor(createSensor());
		value.setTemp(32.5);
		value.setRaining(true);
		value.setId(50000);
		return value;
	}
	
	private void deleteSensor() {
		sensorsService.deleteSensor(sensorsService.findByName(nameSensor).get().getId());
	}
	
	
	@Test
	void addValueSensorTest() {
		ValueSensor value = createValueSensor();
		valuesSensorService.saveValueSensor(value);
		
		Mockito.verify(valuesSensorRepository, Mockito.times(1)).save(value);
		deleteSensor();
	}
	
	@Test
	void deleteValueSensorTest() {
		ValueSensor value = createValueSensor();

		valuesSensorService.deleteValueSensor(value.getId());
		
		Mockito.verify(valuesSensorRepository, Mockito.times(1)).deleteById(value.getId());
		deleteSensor();
	}
	

	



}
