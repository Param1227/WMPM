package at.ac.wmpm.booking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.model.TrainTicket;
import at.ac.wmpm.booking.model.Ticket;

public class TicketProcessing implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(TicketProcessing.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		TrainTicket t = exchange.getIn().getBody(TrainTicket.class);

		LOG.info("ticket:" + exchange);
		LOG.info("ticket:" + t);
		try {

			Ticket result = new Ticket();
			result.setId(t.getId().toString());
			result.setDate(t.getDate().toString());
			result.setFrom(t.getFrom());
			result.setTo(t.getTo());
			result.setCategory(t.getCategory());
			result.setName("Test");
			result.setEmail("test.test@gmail.com");

			exchange.getOut().setBody(result);
		} catch (Exception e) {
			exchange.getIn().setHeader("error","error");
		}

	}

}
