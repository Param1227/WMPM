package at.ac.wmpm.booking.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StornoID {
	
	@JsonProperty("id")
	private String id;
	
	
	public StornoID() {
	
	}

	public StornoID(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	public String getIdasString() {
//		return this.toString();
//	}

	

}
