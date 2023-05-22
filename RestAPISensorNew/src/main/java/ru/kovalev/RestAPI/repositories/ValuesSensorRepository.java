package ru.kovalev.RestAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.kovalev.RestAPI.models.ValueSensor;

public interface ValuesSensorRepository extends JpaRepository<ValueSensor, Integer>{

	public List<ValueSensor> findByRaining(boolean raining);
	
}
