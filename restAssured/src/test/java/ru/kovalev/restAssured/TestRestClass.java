package ru.kovalev.restAssured;


import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;




public class TestRestClass {
	private String baseUrl = "http://45.8.159.36";
	private String nameSensor = "NewSensor123";
	private int basePort = 8080;
	private String nameSensor1 = "firstSensorFromPostman";
	private String pathSensor2 = "sensors/1";
	private String pathSensors = "/sensors";
	private String pathValuesSensor = "/measurements";
	private String pathValues = "/measurements/501";
	private String pathRainyDays = "/measurements/rainyDaysCount";
	private int sizeArrayValuesSensors = 501;
	private String rainyDaysCount = "2";
	

	
	
	private Response getResponse(String path) {
		 Response response = given()
					.contentType(ContentType.JSON)
					.baseUri(baseUrl)
					.port(basePort)
					.when()
					.get(path);
		 return response;
	}
	
	@Test
	void getSensorTest() {
		Response response = getResponse(pathSensor2);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), nameSensor);
	}
	
	@Test
	void sizeSensorsTest() {
		int sizeSensors = getResponse(pathSensors)
				.then().log().body()
				.extract().jsonPath().getList("name").size();
		
		Assert.assertEquals(sizeSensors, 2);	
	}
	
	@Test
	void getSizeValuesSensorTest() {
		int sizeValues = getResponse(pathValuesSensor)
				.then().log().body()
				.extract().jsonPath().getList("measurements").size();
		Assert.assertEquals(sizeValues, sizeArrayValuesSensors);
	}
	
	@Test 
	void getRainyDaysTest(){
		Response response = getResponse(pathRainyDays);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().asString(), rainyDaysCount);
	}
	
	@Test
	void getValueSensorTest() {
		Response response = getResponse(pathValues);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("sensor.name"), nameSensor1);
	}
	
	
}
