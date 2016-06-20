package at.ac.wmpm.trainbooking.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Ride {
	
	private UUID id;
	private String from;
	private String to;

	private Date date;

	// in minutes
	private int duration;


	public Ride(String from, String to, Date date, int duration) {
		this.id = UUID.randomUUID();
		this.from = from;
		this.to = to;
		this.date = date;
		this.duration = duration;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", from=" + from + ", to=" + to + ", date="
				+ date + ", duration=" + duration + "]";
	}
}
