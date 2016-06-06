package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

import at.ac.wmpm.booking.processor.PreparePathRequestProcessor;

public class GetTrainsRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("direct:getTrains")
		.multicast(new GroupedExchangeAggregationStrategy())
        .parallelProcessing() //as we do not want to have seq processing
        .onPrepareRef("preparePathRequestProcessor")
            .enrich("direct:getTrain1")	//http://localhost:8082/request/")
            .enrich("direct:getTrain2")	//http://localhost:8083/request/")
       .end();
	}
}
