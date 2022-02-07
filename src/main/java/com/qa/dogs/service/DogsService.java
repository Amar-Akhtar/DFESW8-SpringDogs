package com.qa.dogs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.dogs.domain.Dogs;
import com.qa.dogs.repo.DogRepo;

@Service
public class DogsService implements CRUDInterface<Dogs> {
	
	private DogRepo repo;
	
	public DogsService(DogRepo repo) {
		this.repo = repo;
		
	}
//Create
	@Override
	public Dogs create(Dogs t) {
		
		return this.repo.save(t);
	}
	
//Read
	@Override
	public List<Dogs> getAll() {
		
		return this.repo.findAll();
	}
	
//Read-By-Id
	@Override
	public Dogs getById(Long id) {
		Optional<Dogs> optDog = this.repo.findById(id);
		return optDog.get();
	}
	
//Update
	@Override
	public Dogs update(Long id, Dogs t) {
		Optional<Dogs> optDog = this.repo.findById(id);
		if(optDog.isPresent()) {
			Dogs existingDog = optDog.get();
			existingDog.setName(t.getName());
			existingDog.setAge(t.getAge());
			return this.repo.save(existingDog);
		}
		return null;
	}
	
//Delete

	@Override
	public Dogs delete(Long id) {
		Optional<Dogs> toDelete = this.repo.findById(id);
		this.repo.deleteById(id);
		return toDelete.orElse(null);
	}
	

	

}
