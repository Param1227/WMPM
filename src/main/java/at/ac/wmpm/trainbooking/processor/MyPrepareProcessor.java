package at.ac.wmpm.trainbooking.processor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.meta.Exhaustive;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPrepareProcessor implements Processor {
	
	private static final Logger LOG = LoggerFactory.getLogger(MyPrepareProcessor.class);
   
	@Override
    public void process(Exchange exchange) throws Exception {
		LOG.info("Komme in MyPrepareProcessor");
		Map<String,Object> headers = new HashMap<String,Object>();
		String failedEndpoint = exchange.getProperty(Exchange.TO_ENDPOINT, String.class);
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        headers.put("FailedBecause", cause.getMessage());
        headers.put("FailedRoute", failedEndpoint);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
		exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
        exchange.getIn().setHeader("FailedBecause", cause.getMessage());
        exchange.getIn().setHeader("FailedRoute", failedEndpoint);
        exchange.getIn().setBody("Something bad happened");
    }

}
