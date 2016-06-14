package at.ac.wmpm.booking.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferEnriched implements Serializable{
	@JsonProperty("offers")
	private List<Offer> offers;
	@JsonProperty("days")
	private List<Day> days;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Day implements Serializable {
		private String day;
		
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
		}
	
		@Override
		public String toString() {
			return "Day [day=" + day + "]";
		}

	}

	public List<Offer> getOffer() {
		return offers;
	}
	public void setOffer(List<Offer> offer) {
		this.offers = offer;
	}
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
	@Override
	public String toString() {
		return "OfferEnriched [offers=" + offers + ", days=" + days + "]";
	}

}

