package com.qa.dogs.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")

public class DogServiceUnitTest {

	
	private Dogs newDogs;
	private Dogs savedDogs;
	
	
	@Autowired
	private DogService service;
	
	@MockBean
	private DogRepo repo;
	
	@BeforeEach
	void setUp() {
		newDogs = new Dogs("Cadie", 2);
		savedDogs= new Dogs(1L, "Cadie", 2);
		this.service.create(newDogs);
	}
	
	@Test
	void testCreate() {
		Mockito.when(this.repo.save(newDogs)).thenReturn(savedDogs);
		
		assertThat(this.service.create(newDogs)).isEqualTo(savedDogs);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newDogs);
	}
}
