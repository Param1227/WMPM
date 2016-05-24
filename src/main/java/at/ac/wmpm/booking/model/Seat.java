package at.ac.wmpm.booking.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Seat {

	private UUID id;
	private Category category;
	private BigDecimal price;
	private boolean booked;
	
	
	public Seat (Category category, BigDecimal price, boolean window) {
		this.id = UUID.randomUUID();
		this.category = category;
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
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
		return "Seat [id=" + id + ", category=" + category + ", price=" + price
				+ ", booked=" + booked + "]";
	}
}
