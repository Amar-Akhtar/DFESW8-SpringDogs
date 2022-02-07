package com.qa.dogs.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class DogUnitTest {
	
	
	@Test
	public void testEquals() {
		Dogs cadie = new Dogs (1L, "Cadie", 2);
		
		assertNotNull(cadie.getId());
		assertNotNull(cadie.getName());
		assertNotNull(cadie.getAge());
		
		assertEquals((Long)1L, cadie.getId());
		assertEquals("Cadie", cadie.getName());
		assertEquals(3, cadie.getAge());
		
		
		
	}
	
	@Test
	public void testToString() {
		Dogs cadie = new Dogs(1L, "Cadie", 2);
		assertEquals("Dogs [id=1, name=Cadie, age=2]", cadie.toString());
	}

}
