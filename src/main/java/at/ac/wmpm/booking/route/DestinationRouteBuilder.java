package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.booking.processor.DestinationsJson2Pojo;

public class DestinationRouteBuilder extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        
        DestinationsJson2Pojo destinationJson2Pojo = new DestinationsJson2Pojo();
        
        from("direct:processDestinations").enrich("mongodb:mongoDB?database=trainbooking&collection=trainstations&operation=findAll")
        .process(destinationJson2Pojo).marshal();
    }
    
}