package com.auth.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.models.User;
import com.auth.repositories.UserRepository;
import com.auth.request.UserGymRequest;
import com.auth.request.UserRequest;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class UserController 
{
	
	@Autowired
	UserRepository userRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/user/all")
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/user/getuser")
	public User getbyid(@RequestBody UserRequest request)
	{
		return userRepository.findById(request.getId()).get();	
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/user/delete")
	public boolean deleteClass(@RequestBody UserRequest request)
	{
		try
		{
			userRepository.deleteById(request.getId());
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;	
	}
	
	
	
}

