package at.ac.wmpm.trainbooking.model;

import java.util.UUID;

public class Weather {
	
	private UUID id;
	private String city;
	private String weather;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Weather(String city, String weather) {
        this.city = city;
        this.weather = weather;
	}

	
	@Override
	public String toString() {
		return "Weather [id=" + id + ", city=" + city + ", weather=" + weather + "]";
	}
}
