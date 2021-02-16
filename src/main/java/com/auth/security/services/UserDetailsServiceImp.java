package com.auth.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.models.User;
import com.auth.repositories.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("username : "+ username +"not found");
			
		}
		
		return UserDetailsImp.build(user);
		
	}

}
