package com.auth.request;

public class GymRequest
{
	private String name;
	private String trainerid;
	
	public GymRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public GymRequest(String name, String trainerid) {
		super();
		this.name = name;
		this.trainerid = trainerid;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrainerid() {
		return trainerid;
	}
	public void setTrainerid(String trainerid) {
		this.trainerid = trainerid;
	}
	
	

}
