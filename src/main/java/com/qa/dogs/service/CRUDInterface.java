package com.qa.dogs.service;

import java.util.List;

import com.qa.dogs.domain.Dogs;

public interface CRUDInterface<T> {
	
	//Create
	T create (T t);
	
	//Read All
	List<T> getAll();
	
	//Read by ID
	T getById(Long id);
	
	//Update
	T update (Long id, T t);
	
	//Delete
	Dogs delete(Long id);
}
