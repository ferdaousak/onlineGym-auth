package com.auth.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.auth.models.Gymclass;

public interface GymclassRepository extends MongoRepository<Gymclass, String>
{
	Gymclass findByName(String name);
	
	List<Gymclass> findByTrainerid(String trainerid);
}
