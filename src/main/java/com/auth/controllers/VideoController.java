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
import com.auth.request.VideoIdRequest;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {
	//just

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
	@PostMapping("/id")
	public Video getById(@RequestBody VideoIdRequest request)
	{
		return vidrepo.findById(request.getId()).get();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/title")
	public Video getByTitle(@RequestBody VideoGymRequest request)
	{
		return vidrepo.findByTitle(request.getTitle());
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/gym/all")
	public List<Video> getAllGymVideos(@RequestBody VideoIdRequest request)
	{
		System.out.println("request.id (/gym/all):" + request.getId());
		Gymclass gym = gymrepo.findById(request.getId()).get();
		
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
