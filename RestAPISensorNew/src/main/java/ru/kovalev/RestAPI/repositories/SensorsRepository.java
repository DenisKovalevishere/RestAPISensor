package ru.kovalev.RestAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.kovalev.RestAPI.models.Sensor;

public interface SensorsRepository extends JpaRepository<Sensor, Integer>{

	Optional <Sensor> findByName(String name);
	
}
