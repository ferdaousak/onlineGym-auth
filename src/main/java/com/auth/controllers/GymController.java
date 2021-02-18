package com.auth.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.auth.models.User;
import com.auth.models.Video;
import com.auth.repositories.GymclassRepository;
import com.auth.repositories.UserRepository;
import com.auth.repositories.VideoRepository;
import com.auth.request.UserGymRequest;
import com.auth.request.VideoGymRequest;
import com.auth.request.GymRequest;


@RestController
@RequestMapping("/api/gym")
@CrossOrigin(origins = "*")
public class GymController
{
	
	@Autowired
	GymclassRepository gymrepo;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	VideoRepository vidrepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<Gymclass> getAll()
	{
		return gymrepo.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Gymclass getById(@PathParam("id") String id)
	{
		Optional<Gymclass> gym = gymrepo.findById(id);
		
		return gym.get();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/name/{name}")
	public Gymclass getByName(@PathParam("name") String name)
	{
		return gymrepo.findByName(name);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/add")
	public Gymclass SaveClass(@RequestBody GymRequest gym)
	{
		return gymrepo.save(new Gymclass(gym.getName(),gym.getTrainerid()));
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/update")
	public Gymclass updateClass(@RequestBody Gymclass gym)
	{
		return gymrepo.save(gym);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/delete")
	public boolean deleteClass(@RequestBody GymRequest request )
	{
		try
		{
			Gymclass gym = gymrepo.findByName(request.getName());
			System.out.println(gym);
			gymrepo.delete(gym);
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;	
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/add/user")
	public Gymclass addUserToGym(@RequestBody UserGymRequest request)
	{
		Gymclass gym = null;
		try
		{
			 gym = gymrepo.findById(request.getGymid()).get();
			 User user = userrepo.findByUsername(request.getUsername());
		 
			 gym.getUserids().add(user.getId());
			 
			 user.getGymsjoined().add(gym.getId());
			 
			 userrepo.save(user);
			 gymrepo.save(gym); 
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return gym;
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/remove/user")
	public Gymclass removeUserFromGym(@RequestBody UserGymRequest request)
	{
		Gymclass gym = null;
		try
		{
			 gym = gymrepo.findById(request.getGymid()).get();
			 User user = userrepo.findByUsername(request.getUsername());
			 
			 gym.getUserids().remove(user.getId());
			 user.getGymsjoined().remove(gym.getId());
			 userrepo.save(user);
			 gymrepo.save(gym);	 
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return gym;	
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/user/all")
	public List<Gymclass> getAllGymsOfUser(@RequestBody UserGymRequest request)
	{
		User user = userrepo.findByUsername(request.getUsername());
		List<String> ids = user.getGymsjoined();
		
		List<Gymclass> gyms = new ArrayList<Gymclass>();
		for(String id: ids)
		{
			gyms.add(gymrepo.findById(id).get());
		}
		
		return gyms;
	}
	
	
		
}
