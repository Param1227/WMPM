package at.ac.wmpm.booking.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class Offer implements Serializable, Comparable<Offer> {

	private UUID id;
	private String from;
	private String to;
	private String train;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date date;

	// duration is displayed in minutes
	private int duration;

	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal price;
	private Category category;
	
	public Offer() {
		
	}
	
	public Offer(String from, String to, Date date,
			int duration, Category category, BigDecimal price) {
		this.from = from;
		this.to = to;
		//this.train = train;
		this.date = date;
		this.duration = duration;
		this.price = price;
		this.category = category;
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
	

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
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

	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", from=" + from + ", to=" + to + ", train=" + train + ", date=" + date
				+ ", duration=" + duration + ", category=" + category + ", price=" + price + "]";
	}
	
	@Override
	public int compareTo(Offer o) {
		return this.price.compareTo(o.price);
	}

}
