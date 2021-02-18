package com.auth.request;

public class VideoGymRequest {

	private String gymid;
	private String title;
	
	public VideoGymRequest(String gymid, String title) {
		super();
		this.gymid = gymid;
		this.title = title;
	}
	public VideoGymRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGymid() {
		return gymid;
	}
	public void setGymid(String gymid) {
		this.gymid = gymid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
