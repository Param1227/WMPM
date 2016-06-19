package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreparePathRequestProcessor  implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(PreparePathRequestProcessor.class);    
    @Override
    public void process(Exchange exchange) throws Exception {
    	
		exchange.getOut().setHeader("CamelHttpPath", exchange.getIn().getHeader("date") + "/" + exchange.getIn().getHeader("from") + "/" + exchange.getIn().getHeader("to"));
    	exchange.getOut().setHeader("CamelHttpMethod", "GET");
    	exchange.getOut().setBody(null);
    }


}
