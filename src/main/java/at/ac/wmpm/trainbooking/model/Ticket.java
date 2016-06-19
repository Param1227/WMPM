package at.ac.wmpm.trainbooking.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String from;
	private String to;
	private String date;
	private String email;
	private Category category;

	public Ticket() {
	}

	public Ticket(String name, String from, String to) {
		this.name = name;
		this.from = from;
		this.to = to;
	}

	public Ticket(String name, String from, String to, String date) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Ticket(String id, String name, String from, String to, String date) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", name=" + name + ", from=" + from + ", to=" + to + ", date=" + date + ", email=" + email
				+ ", category=" + category + "]";
	}

}
