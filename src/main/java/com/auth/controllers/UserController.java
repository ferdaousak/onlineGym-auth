package com.auth.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.models.User;
import com.auth.repositories.UserRepository;

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
	@DeleteMapping("/user/delete/{id}")
	public boolean deleteClass(@PathParam("id") String id)
	{
		try
		{
			userRepository.deleteById(id);
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;	
	}
	
	
	
}

