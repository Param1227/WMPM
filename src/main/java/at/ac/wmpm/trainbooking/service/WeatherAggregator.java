package at.ac.wmpm.trainbooking.service;

import java.io.IOException;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.wmpm.trainbooking.model.Offer;
import at.ac.wmpm.trainbooking.model.AggregatedOffer;
import at.ac.wmpm.trainbooking.model.Weather;


public class WeatherAggregator implements AggregationStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherAggregator.class);

	@Override
	public Exchange aggregate ( Exchange original , Exchange newExchange ) {		
		   @SuppressWarnings("unchecked")
		   List<Offer> originalBody = (List<Offer>) original.getIn().getBody(List.class);
		   String newResponse = newExchange.getIn().getBody(String.class);
		   LOG.info("This is the newResponse:");
		   Object merge = (originalBody + newResponse);
		   if (original.getPattern().isOutCapable()) {
	            original.getOut().setBody(merge);
	        } else {
	            original.getIn().setBody(merge);
	        }
	        return original;
	    }
	     
	}
//		@SuppressWarnings("unchecked")
//		List<Offer> offers = (List<Offer>) original.getIn().getBody(List.class);
//		AggregatedOffer aggregatedOffer = new AggregatedOffer();
//		aggregatedOffer.setOffer(offers);
//
//		ObjectMapper mapper = new ObjectMapper();
//		String newBody = newExchange.getIn().getBody(String.class);
//		Weather weather = new Weather(newBody, newBody);      
//		try {
//			weather = mapper.readValue(newBody, new TypeReference<Weather>() { });
//
//		} catch (IOException e) { }
//		LOG.info("DAMN!");
//		if(weather.getCity() != null) {
//			return newExchange;
//		}
//
//		original.getIn().setBody(aggregatedOffer);
//		return original;
//	}
//}
