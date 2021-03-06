package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLetterProcessor implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(DeadLetterProcessor.class);
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody("Something bad happened");
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
	}
}

