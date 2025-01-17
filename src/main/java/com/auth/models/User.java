package com.auth.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user")
public class User 
{
	@Id
	private String id;
	@Indexed(unique=true, direction = IndexDirection.DESCENDING, dropDups= true)
	private String username;
	private String email;
	private String password;
	
	private List<String> Gymsjoined;
	private Set<Role> roles = new HashSet<>();
	
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.Gymsjoined = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<String> getGymsjoined() {
		return Gymsjoined;
	}

	public void setGymsjoined(List<String> gymsjoined) {
		Gymsjoined = gymsjoined;
	}
	
	
}
