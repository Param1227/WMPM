package at.ac.wmpm.trainbooking.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.wmpm.trainbooking.model.Offer;

import com.fasterxml.jackson.core.type.TypeReference;


public class MapOffers implements Processor {

	
	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			// create new jackson mapper
			ObjectMapper mapper = new ObjectMapper();

			List<Exchange> grouped = exchange.getProperty(Exchange.GROUPED_EXCHANGE, List.class);

			List<Offer> offers = new ArrayList<Offer>();

			for (Exchange e : grouped) {
				List<Offer> list = mapper.readValue(e.getIn().getBody(String.class), new TypeReference<List<Offer>>() {});
				if(list != null) {
					offers.addAll(list);
				}
			}

			if (offers != null) {
				Collections.sort(offers);
				exchange.getOut().setHeader("to", offers.get(0).getTo());
			}


			exchange.getOut().setBody(offers);
		} catch (Exception e) {
			exchange.getOut().setBody(null);
		}

	}

}
