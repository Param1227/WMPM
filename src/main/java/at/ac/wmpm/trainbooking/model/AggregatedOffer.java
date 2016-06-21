package at.ac.wmpm.trainbooking.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import at.ac.wmpm.trainbooking.model.Offer;

public class AggregatedOffer implements Serializable{
	@JsonProperty("offers")
	private List<Offer> offers;
	private String weather;
	
	public List<Offer> getOffer() {
		return offers;
	}
	public void setOffer(List<Offer> offer) {
		this.offers = offer;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	@Override
	public String toString() {
		return "AggregatedOffer [offers=" + offers + ", weather=" + weather + "]";
	}
}
