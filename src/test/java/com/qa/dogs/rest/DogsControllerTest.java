package com.qa.dogs.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:dogs-schema.sql", "classpath:dogs-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogsControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper map;
	
	@Test
	void testCreateCont() throws Exception{
		Dogs newDog = new Dogs("Cadie", 2);
		
		String newDogJSON = this.map.writeValueAsString(newDog);
		
		RequestBuilder mockRequest = post("/createDog").contentType(MediaType.APPLICATION_JSON);
		
		Dogs savedDog = new Dogs (2L, "Cadie", 2);
		
		String savedDogJSON = this.map.writeValueAsString(savedDog);
		
		ResultMatcher matchStatus = status().isCreated();
		
		ResultMatcher matchBody = content().json(savedDogJSON);
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	
	
	
	}
	@Test
	void readTest() throws Exception{
		Dogs readDog = new Dogs(1L, "Cadie", 2);
		List<Dogs> allDogs = List.of(readDog);
		String readDogJSON = this.map.writeValueAsString(allDogs);
		
		RequestBuilder readRequest = get("/getdogs");
		
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(readDogJSON);
		
		this.mock.perform(readRequest).andExpect(status).andExpect(body);
		
		
	}
	
	@Test
	void updateTest() throws Exception{
		Dogs updateDog = new Dogs("Lola", 1);
		String updateDogJSON = this.map.writeValueAsString(updateDog);
		Long IdUpdate = 1L;
		
		RequestBuilder updateRequest = put("/updatedog" + IdUpdate).contentType(MediaType.APPLICATION_JSON).content(updateDogJSON);
		
		Dogs retUpdatedDog = new Dogs(1L, "Lola", 1);
		String retUpdatedDogJSON = this.map.writeValueAsString(retUpdatedDog);
		
		ResultMatcher retStatus = status().isOk();
		ResultMatcher retBody = content().json(retUpdatedDogJSON);
		
		this.mock.perform(updateRequest).andExpect(retStatus).andExpect(retBody);
		
	
	
	
	}
	
	@Test
	void deleteTest() throws Exception {
		Dogs deleteDog = new Dogs(1L, "Cadie", 2);
		String deleteDogJSON = this.map.writeValueAsString(deleteDog);
		
		Long removeId = 1L;
		RequestBuilder deleteRequest = delete("/removeDog/" + removeId);
		ResultMatcher Status = status().isOk();
		ResultMatcher Body = content().json(deleteDogJSON);
		
		this.mock.perform(deleteRequest).andExpect(Status).andExpect(Body);
		
	}
	

}
