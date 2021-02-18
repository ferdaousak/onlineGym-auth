package com.auth.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Videos")
public class Video
{
	@Id
	private String id;
	
	@Indexed(unique=true, direction = IndexDirection.DESCENDING, dropDups= true)
	private String title;
	private String url;
	private String gymclassid;
	

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(String title, String url, String gymclass_id) {
		super();
		this.title = title;
		this.url = url;
		this.gymclassid = gymclass_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGymclassid() {
		return gymclassid;
	}
	public void setGymclassid(String gymclass_id) {
		this.gymclassid = gymclass_id;
	}
}
