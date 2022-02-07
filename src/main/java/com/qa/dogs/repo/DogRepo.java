package com.qa.dogs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.dogs.domain.Dogs;

@Repository
public interface DogRepo extends JpaRepository<Dogs, Long>{
	
	
	
	}


