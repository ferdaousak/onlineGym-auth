package com.auth.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.models.Gymclass;
import com.auth.models.Video;
import com.auth.repositories.GymclassRepository;
import com.auth.repositories.VideoRepository;
import com.auth.request.GymRequest;
import com.auth.request.VideoGymRequest;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {

	@Autowired
	VideoRepository vidrepo;
	
	@Autowired
	GymclassRepository gymrepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<Video> getAll()
	{
		return vidrepo.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Video getById(@PathParam("id") String id)
	{
		return vidrepo.findById(id).get();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{title}")
	public Video getByTitle(@PathParam("title") String title)
	{
		return vidrepo.findByTitle(title);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/gym/all")
	public List<Video> getAllGymVideos(@RequestBody GymRequest request)
	{
		
		Gymclass gym = gymrepo.findByName(request.getName());
		
		return vidrepo.findByGymclassid(gym.getId());
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/add")
	public Video add(@RequestBody VideoGymRequest request)
	{
		Gymclass gym = gymrepo.findById(request.getGymid()).get();
		Video vid = new Video(request.getTitle(), "uploads/"+request.getTitle()+".mp4", gym.getId());
		vidrepo.save(vid);
		
		gym.getVideoids().add(vid.getId());
		
		gymrepo.save(gym);
		
		return vid;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/update")
	public Video update(@RequestBody Video video)
	{
		return vidrepo.save(video);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/gym/remove")
	public Gymclass removeVideoFromGym(@RequestBody VideoGymRequest request)
	{
		Gymclass gym = null;
		try
		{
			 gym = gymrepo.findById(request.getGymid()).get();
			 
			 Video vid = vidrepo.findByTitle(request.getTitle());
			 
			 gym.getVideoids().remove(vid.getId());
			 
			 gymrepo.save(gym);
			 vidrepo.delete(vid);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return gym;
	}

	
	
}
