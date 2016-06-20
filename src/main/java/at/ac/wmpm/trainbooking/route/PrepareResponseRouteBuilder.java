package at.ac.wmpm.trainbooking.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class PrepareResponseRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
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
