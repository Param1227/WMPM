package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

import at.ac.wmpm.trainbooking.processor.PreparePathRequestProcessor;

public class GetTrainsRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:getTrains")
		.multicast(new GroupedExchangeAggregationStrategy())
        .parallelProcessing() 
        .onPrepareRef("preparePathRequestProcessor")
            .enrich("direct:getTrain1")	
            .enrich("direct:getTrain2")	
       .end();
	}
}
