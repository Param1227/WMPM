package at.ac.wmpm.trainbooking.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.trainbooking.processor.MyPrepareProcessor;

public class PrepareResponseRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		errorHandler(deadLetterChannel("jms:queue:dead")
			    .maximumRedeliveries(3).redeliveryDelay(1000).onPrepareFailure(new MyPrepareProcessor()));
		
		from("direct:prepareResponse")
		.setBody(simple("null")) 
		.removeHeaders("CamelHttp*")
		.setHeader("CamelHttp", simple("${header.date}/${header.from}/${header.to}"))
		.log("${header.date}/${header.from}/${header.to}")
		.setHeader("CamelHttpMethod", constant("GET"))
		.enrich("direct:getTrains")
		.setBody(exchangeProperty(Exchange.GROUPED_EXCHANGE))
		.log("EXCHANGE-HEADER: ${header.date}}")
		.log("EXCHANGE-BODY: ${body}")
		.to("direct:forward");
	}

}
