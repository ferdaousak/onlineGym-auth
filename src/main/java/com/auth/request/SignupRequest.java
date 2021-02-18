package com.auth.request;

import java.util.Set;

public class SignupRequest 
{
	private String username;
	private String password;
	private String email;
	private Set<String> role;
	private String userrole = "";
	    
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userRole) {
		this.userrole = userRole;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SignupRequest(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
