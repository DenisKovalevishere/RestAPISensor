package ru.kovalev.RestAPI.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SensorsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	private String sensorsUrl = "/sensors";
	private String sensorUrl = "/sensors/2";

	
	private void getStatus(String url) {
		try {
			this.mockMvc.perform(get(url))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("name")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void sensorsTest() {
		getStatus(sensorsUrl);		
	}
	
	
	@Test 
	void getSensorTest(){
		getStatus(sensorUrl);
	}

}
