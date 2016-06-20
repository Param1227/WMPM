package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrepareStornoID implements Processor{

    private static final Logger LOG = LoggerFactory.getLogger(PrepareStornoID.class);
    private StornoProcessor stornoProcessor;

    @Override
    public void process(Exchange exchange) throws Exception {
        Object id = exchange.getIn().getBody();
        LOG.info("Body = " + id.toString());
        stornoProcessor.stornoTicket(id.toString());
    }

    public void setStornoProcessor(StornoProcessor stornoProcessor) {
        this.stornoProcessor = stornoProcessor;
    }
}
