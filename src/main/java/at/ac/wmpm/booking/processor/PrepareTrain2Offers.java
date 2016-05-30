package at.ac.wmpm.booking.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.ac.wmpm.booking.model.Offer;

public class PrepareTrain2Offers implements Processor{

	private static final Logger LOG = LoggerFactory.getLogger(PrepareTrain2Offers.class);

	@Override
	public void process(Exchange exchange) throws Exception {

		// create new jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		LOG.debug(exchange.toString());
		List<Offer> offers = mapper.readValue(exchange.getIn().getBody(String.class), new TypeReference<List<Offer>>() { });
		List<Offer> result = new ArrayList<Offer>();
		LOG.debug("PrepareTrain2");
		if (offers != null) {
			for (Offer o : offers) {
				//TicketMapper.registerOffer(o, "Airline1");
				LOG.debug("Offer: " + o.getFrom());
				o.setTrain("db");
				result.add(o);
			}
			
			exchange.getOut().setBody(result);
		}

	}

}
