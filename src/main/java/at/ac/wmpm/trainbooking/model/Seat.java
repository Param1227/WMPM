package at.ac.wmpm.trainbooking.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Seat {

	private UUID id;
	private Category cat;
	private BigDecimal price;
	private boolean booked;
	
	
	public Seat (Category cat, BigDecimal price, boolean booked) {
		this.id = UUID.randomUUID();
		this.cat = cat;
		this.price = price;
		this.booked = false;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Category getCategory() {
		return cat;
	}
	
	public void setCategory(Category cat) {
		this.cat = cat;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", category=" + cat + ", price=" + price
				+ ", booked=" + booked + "]";
	}
}
