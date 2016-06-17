package at.ac.wmpm.booking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.model.Ticket;

public class PersistTicket implements Processor {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersistTicket.class);

	@Override
	public void process(Exchange ex) throws Exception {
		
		Ticket ticket = ex.getIn().getBody(Ticket.class);
		
		LOG.info("Lukas Persist:"+ticket);
		
		ex.getOut().setBody(ticket);
		
	}
	
	
}
