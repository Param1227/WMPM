package at.ac.wmpm.trainbooking.processor;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.trainbooking.model.Ticket;


public class TwitterProcessor  implements Processor{
	
	private static final Logger LOG = LoggerFactory.getLogger(TwitterProcessor.class);

	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Ticket ticket = exchange.getIn().getBody(Ticket.class);
		LOG.info("TwitterProcessorBody: " + exchange.getIn().getBody());
		LOG.debug("TicketDate:"+ ticket.getDate());

		Date d = new Date();
		String unitime = ""+d.getTime();
		
		
		String str = ticket.getName() + " has booked a "+ticket.getCategory()+"-Class Trainticket from " + ticket.getFrom() +
		" to " + ticket.getTo() + " on "+ticket.getDate()+" #time"+unitime;
	
		if(str.length() > 140) {
            exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            exchange.getOut().setBody("Cannot post on twitter because length of the name oder e-mail adress is to long! (length:"+str.length()+")");
        } else {
			LOG.debug("Twitterlength:" + str.length());
			exchange.getOut().setBody(str);
        }
		

	}

}



