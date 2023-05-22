package ru.kovalev.RestAPI.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import ru.kovalev.RestAPI.models.Sensor;
import ru.kovalev.RestAPI.repositories.SensorsRepository;

@SpringBootTest

@TestPropertySource("/application-test.properties")
class SensorsServiceTest {

	@Autowired
	private SensorsService sensorsService;
	
	private String nameSensor = "nameSensor";
	private String newNameSensor = "newNameSensor";
	
	@MockBean
	private SensorsRepository sensorsRepository;
	
	private Sensor createSensor() {
		Sensor sensor = new Sensor();
		sensor.setName(nameSensor);
		return sensor;
	}

	@Test
	void createNewSensorTest() {
		Sensor sensor = createSensor();
		sensorsService.saveSensor(sensor);
		
		Mockito.verify(sensorsRepository, Mockito.times(1)).save(sensor);
	}
	
	@Test
	void updateSensorTest() {
		Sensor sensor = createSensor();
		sensorsService.saveSensor(sensor);
		Sensor newSensor = createSensor();
		newSensor.setName(newNameSensor);
		sensorsService.updateSensor(newSensor, sensor.getId());
		
		Mockito.verify(sensorsRepository, Mockito.times(1)).save(sensor);
		Mockito.verify(sensorsRepository, Mockito.times(1)).save(newSensor);
	}
	
	@Test
	void deleteSensorTest() {
		Sensor sensor = createSensor();
		sensorsService.deleteSensor(sensor.getId());
		
		Mockito.verify(sensorsRepository, Mockito.times(1)).deleteById(sensor.getId());
	}
	
	

}
