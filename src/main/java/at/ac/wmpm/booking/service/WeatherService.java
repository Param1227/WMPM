package at.ac.wmpm.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.model.Weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherService {
    
    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);
    
    public WeatherService() throws Exception {
        
    }
    
    /**
     * Returns the weather of the location within the next 7 days.
     *
     * @param location the preferred destination
     * @throws Exception
     */
    public Weather getWeather(String location) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        String json = ProducerTemplateProvider.getTemplate()
        .requestBodyAndHeader("direct:weatherProducer", "", "CamelWeatherLocation", location, String.class);
        Weather weather = mapper.readValue(json, new TypeReference<Weather>() { });
        
        return weather;
    }
    
}