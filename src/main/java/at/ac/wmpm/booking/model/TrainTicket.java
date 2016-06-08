package at.ac.wmpm.booking.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class TrainTicket {
	
	private UUID id;
	private String from;
	private String to;
	private Date date;
	private Category category;
	private BigDecimal price;
	
	public TrainTicket() {
		
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

	@Override
	public String toString() {
		return "TrainTicket [id=" + id + ", from=" + from + ", to=" + to
				+ ", date=" + date + ", category=" + category + ", window="
				+ ", price=" + price + "]";
	}
	

}
