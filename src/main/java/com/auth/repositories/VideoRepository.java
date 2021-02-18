package com.auth.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.auth.models.Video;

public interface VideoRepository extends MongoRepository<Video, String>
{
	Video findByTitle(String title);
	
	List<Video> findByGymclassid(String gymclass_id);
	
}
