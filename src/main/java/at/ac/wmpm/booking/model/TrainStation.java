package at.ac.wmpm.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

// ignoreUnknown=true -> ignore first "_id"... entry
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainStation implements Serializable{
    @JsonProperty("id")
    private String id;
    @JsonProperty("city")
    private String city;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String toString() {
        return "TrainStaion [id=" + id + ", city=" + city + "]";
    }
    
}
