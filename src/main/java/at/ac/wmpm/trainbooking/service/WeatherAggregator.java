package at.ac.wmpm.trainbooking.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.wmpm.trainbooking.model.Offer;
import at.ac.wmpm.trainbooking.model.Weather;
import at.ac.wmpm.trainbooking.model.AggregatedOffer;


public class WeatherAggregator implements AggregationStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherAggregator.class);

	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate ( Exchange original , Exchange newExchange ) {		
		//		   @SuppressWarnings("unchecked")
		//		   List<Offer> originalBody = (List<Offer>) original.getIn().getBody();
		////		   AggregatedOffer aggregatedOffer = new AggregatedOffer();
		////		   aggregatedOffer.setOffer(originalBody);
		//		   
		//		   String newResponse = newExchange.getIn().getBody(String.class);
		//		   
		//		   LOG.info("This is the newResponse:");
		//		   Object merge = (originalBody + newResponse + " The Weather seems nice. :) We wish you enjoy your journey!");
		//		   
		//		   if (original.getPattern().isOutCapable()) {
		//	            original.getOut().setBody(merge);
		//	        } else {
		//	            original.getIn().setBody(merge);
		//	        }
		//	        return original;
		//	    }


		Object newBody = newExchange.getIn().getBody();
		ArrayList<Object> list = null;
		if (original == null) {
			list = new ArrayList<Object>();
			list.add(newBody);
			newExchange.getIn().setBody(list);
			return newExchange;
		} else {
			list = original.getIn().getBody(ArrayList.class);
			list.add(newBody);
			return original;
		}
	}

}