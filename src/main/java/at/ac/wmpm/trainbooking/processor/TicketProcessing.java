package at.ac.wmpm.trainbooking.processor;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.trainbooking.directory.CustomerMap;
import at.ac.wmpm.trainbooking.model.Customer;
import at.ac.wmpm.trainbooking.model.Ticket;
import at.ac.wmpm.trainbooking.model.TrainTicket;

public class TicketProcessing implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(TicketProcessing.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		TrainTicket t = exchange.getIn().getBody(TrainTicket.class);

		LOG.info("ticket:" + exchange);
		LOG.info("ticket:" + t);
		try {
			Customer customer = CustomerMap.getCustomer(t.getId());

			Ticket result = new Ticket();
			result.setId(t.getId().toString());
			result.setDate(t.getDate().toString());
			result.setFrom(t.getFrom());
			result.setTo(t.getTo());
			result.setCategory(t.getCategory());
			result.setName(customer.getName());

			if (this.isValidEmailAddress(customer.getEmail())) {
				result.setEmail(customer.getEmail());
				exchange.getOut().setBody(result);
			} else {
				exchange.getOut().setBody("E-Mail adress is not valid");
			}
			
		} catch (Exception e) {
			exchange.getIn().setHeader("error","error");
		}

	}
	
	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
