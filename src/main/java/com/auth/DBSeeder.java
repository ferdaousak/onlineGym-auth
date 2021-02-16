package com.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.auth.models.Role;
import com.auth.repositories.RoleRepository;


@Component
public class DBSeeder implements CommandLineRunner 
{
	@Autowired
	private RoleRepository rolerepo;
	
	
	@Override
	public void run(String... args) throws Exception
	{
		rolerepo.deleteAll();
		
		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_TRAINER");
		Role trainerRole = new Role("ROLE_ADMIN");
		
		rolerepo.saveAll(Arrays.asList(userRole,adminRole,trainerRole));
		
	}
}
