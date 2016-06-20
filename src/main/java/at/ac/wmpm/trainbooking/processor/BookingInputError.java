package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingInputError implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(BookingInputError.class);
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		LOG.info("hier test");
		exchange.getOut().setBody("Offer ID is invalid or the ticket has already been booked!");
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "text/plain");
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
	}
}