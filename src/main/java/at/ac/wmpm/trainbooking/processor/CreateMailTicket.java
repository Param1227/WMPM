package at.ac.wmpm.trainbooking.processor;

import at.ac.wmpm.trainbooking.model.Ticket;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateMailTicket implements Processor {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreateMailTicket.class);

	
    @Override
    public void process(Exchange exchange) throws Exception {
        Ticket ticket = exchange.getIn().getBody(Ticket.class);
                
        LOG.info("TicketDate:");


        String body = "This mail contains your ticket: \n\n";

        body += "From: " + ticket.getFrom() + "\n";
        body += "To: " + ticket.getTo() + "\n";
        body += "Name: " + ticket.getName() + "\n";
        body += "Date: " + ticket.getDate() + "\n";
        body += "Category: " + ticket.getCategory() + "\n";
        body += "Have a nice trip!";
        	
         exchange.getOut().setBody(body);
         exchange.getOut().setHeaders(exchange.getIn().getHeaders());

        Map<String, Object> map = new HashMap<String, Object>();

        
        map.put("TO", ticket.getEmail());
        //map.put("TO", "cartoon.entertainin7@gmail.com");
        map.put("From", "wmpm16.g6@gmail.com");
        //map.put("Subject", "FUCK THIS SHIT!");
        map.put("Subject", ticket.getId() + " FUCK Your ticket to " + ticket.getTo());
        exchange.getOut().setHeaders(map);         
    }
}
