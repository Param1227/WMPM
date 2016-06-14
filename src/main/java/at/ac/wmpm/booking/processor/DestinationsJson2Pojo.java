package at.ac.wmpm.booking.processor;

import at.ac.wmpm.booking.model.TrainStation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DestinationsJson2Pojo implements Processor {
    
    private static final Logger LOG = LoggerFactory.getLogger(DestinationsJson2Pojo.class);
    
    
    @Override
    public void process(Exchange exchange) throws Exception {
        
        LOG.debug("Entering DJ2P: " + exchange.getIn().getBody(String.class));
        
        ObjectMapper mapper = new ObjectMapper();
        
        // parse json and generate a list of destinations
        List<TrainStation> destinationsResponse = mapper.readValue(exchange.getIn().getBody(String.class), new TypeReference<List<TrainStation>>() { });
        
        LOG.debug("Destinations: " + destinationsResponse.toString());
        
        exchange.getOut().setBody(destinationsResponse);
        
        LOG.debug("Exiting DJ2P: " + exchange.getOut().getBody(String.class));
    }
}
