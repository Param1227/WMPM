package at.ac.wmpm.booking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DestinationNotFoundErrorJson2Pojo implements Processor {
    
    private static final Logger LOG = LoggerFactory.getLogger(DestinationNotFoundErrorJson2Pojo.class);
    
    
    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.info("hier test" + exchange.getIn().getHeaders());
        exchange.getOut().setBody("Unknown ticket code!");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "text/plain");
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }
}
