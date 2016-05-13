package at.ac.wmpm.booking.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestBean implements Serializable{

	private static final long serialVersionUID = 1L;

    @JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("from")
	private String from;

	@JsonProperty("to")
	private String to;

	public TestBean() {
		
	}

	public TestBean(String id, String name, String from, String to) {
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
