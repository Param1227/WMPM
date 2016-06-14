package at.ac.wmpm.booking.model;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Weather.class);
    
    @JsonProperty("city")
    private City city;
    @JsonProperty("list")
    private List<Liste> list;
    private String location;
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City implements Serializable {
        private String name;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Liste implements Serializable {
        private Temp temp;
        
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Temp implements Serializable {
            
            private double day;
            private double min;
            private double max;
            
            public double getDay() {
                return day;
            }
            
            public void setDay(double day) {
                this.day = Math.round((day - 273.15)*10.0)/10.0;
            }
            
            public double getMin() {
                return min;
            }
            
            public void setMin(double min) {
                this.min = Math.round((min - 273.15)*10.0)/10.0;
            }
            
            public double getMax() {
                return max;
            }
            
            public void setMax(double max) {
                this.max = Math.round((max - 273.15)*10.0)/10.0;
            }
            
            @Override
            public String toString() {
                return day + "C";
            }
        }
        public Temp getTemp() {
            return temp;
        }
        
        public void setTemp(Temp temp) {
            this.temp = temp;
        }
        @Override
        public String toString() {
            return this.temp + "";
        }
    }
    
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
    public List<Liste> getListe() {
        return this.list;
    }
    
    public void setListe(List<Liste> list) {
        this.list = list;
    }
    public String getLocation() {
        return location;
    }
    public String getJson() {
        String json = "";
        if(list != null) {
            json += "{";
            for(Liste temp : this.list) {
                json += "\"day" + list.indexOf(temp) + "\":\"" + temp + "\"";
                if(list.indexOf(temp) != 6) {
                    json += ",";
                }
            }
            json += "}";
        }
        return json;
    }
    @Override
    public String toString() {
        return "{ Information about Weather for next 7 days in " + this.city + this.list;
    }
}