package at.ac.wmpm.trainbooking.model;


public class Weather {
	
	private String city;
	private String weather;
	private Weather newWeather;
	
	
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
		return "Weather [city=" + city + ", weather=" + weather + "]";
	}
	public Weather getNewWeather() {
		return newWeather;
	}
	public void setNewWeather(Weather newWeather) {
		this.newWeather = newWeather;
	}
}
