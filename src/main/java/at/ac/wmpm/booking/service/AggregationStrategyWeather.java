package at.ac.wmpm.booking.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.booking.model.OfferEnriched;
import at.ac.wmpm.booking.model.Weather;
import at.ac.wmpm.booking.model.OfferEnriched.Day;
import at.ac.wmpm.booking.model.Weather.Liste;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AggregationStrategyWeather implements AggregationStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(AggregationStrategyWeather.class);
	@Override
	public Exchange aggregate ( Exchange original , Exchange newExchange ) {		
		
		if ( original == null ) {
			System.out.println("FUCK YEAH!");
			return newExchange ;
		}
		List<Offer> offers = (List<Offer>) original.getIn().getBody(List.class);
		OfferEnriched offerEnriched = new OfferEnriched();
		offerEnriched.setOffer(offers);

		ObjectMapper mapper = new ObjectMapper();
		String newBody = newExchange.getIn().getBody(String.class);
		Weather weather = new Weather();       
		try {
			weather = mapper.readValue(newBody, new TypeReference<Weather>() { });

		} catch (IOException e) { }
		if(weather.getJson() == "") {
			return newExchange;
		}
		List<Day> week = new ArrayList<Day>();
		for(Liste list : weather.getListe()) {
			Day temp = new Day();
			temp.setDay(list.getTemp().getDay()+ "C");
			week.add(temp);
		}
		offerEnriched.setDays(week);
		
		original.getIn().setBody(offerEnriched);
		return original;
	}

}