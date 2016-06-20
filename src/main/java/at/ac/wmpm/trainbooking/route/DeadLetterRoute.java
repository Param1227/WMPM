package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.trainbooking.processor.DeadLetterProcessor;

public class DeadLetterRoute extends RouteBuilder {
	
	private static final Logger LOG = LoggerFactory.getLogger(DeadLetterRoute.class);

	DeadLetterProcessor deadLetterProcessor = new DeadLetterProcessor();
    @Override
    public void configure() throws Exception { 
        from("jms:queue:dead")
        .log("Komme in DeadLetterRoute")
		.process(deadLetterProcessor);          
    }
}