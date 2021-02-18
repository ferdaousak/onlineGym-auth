package com.auth.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Gymclass")
public class Gymclass
{
	@Id
	private String id;
	
	@Indexed(unique=true, direction = IndexDirection.DESCENDING, dropDups= true)
	private String name;
	private String trainerid;
	private List<String> videoids;
	private List<String> userids;
	
	
	public Gymclass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Gymclass(String name, String trainer_id)
	{
		super();
		this.name = name;
		this.trainerid = trainer_id;
		this.videoids = new ArrayList<String>();
		this.userids = new ArrayList<String>();
	}
	
	public Gymclass(String name, String trainer_id, List<String> video_ids,List<String> user_ids) {
		super();
		this.name = name;
		this.trainerid = trainer_id;
		this.videoids = video_ids;
		this.userids = user_ids;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public void setTrainerid(String trainer_id) {
		this.trainerid = trainer_id;
	}
	public List<String> getVideoids() {
		return videoids;
	}
	public void setVideoids(List<String> video_ids) {
		this.videoids = video_ids;
	}
	public List<String> getUserids() {
		return userids;
	}
	public void setUserids(List<String> user_ids) {
		this.userids = user_ids;
	}
	
}
