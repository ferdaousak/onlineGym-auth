package com.auth.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.auth.models.Gymclass;

public interface GymclassRepository extends MongoRepository<Gymclass, String>
{
	Gymclass findByName(String name);
	
	void deleteByName(String name);
	
	List<Gymclass> findByTrainerid(String trainerid);
	
}
