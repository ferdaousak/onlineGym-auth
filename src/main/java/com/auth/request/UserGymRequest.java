package com.auth.request;

public class UserGymRequest
{
	private String gymid;
	private String username;
	
	public UserGymRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserGymRequest(String gymid, String userid) {
		super();
		this.gymid = gymid;
		this.username = userid;
	}
	public String getGymid() {
		return gymid;
	}
	public void setGymid(String gymid) {
		this.gymid = gymid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userid) {
		this.username = userid;
	}
	
	

}
