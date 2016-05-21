package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;

public class PrepareResponseRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		from("direct:prepareResponse")
		.setBody(simple("null")) 
		.removeHeaders("CamelHttp*")
		.setHeader("CamelHttp", simple("${header.date}/${header.from}/${header.to}"))
		.setHeader("CamelHttpMethod", constant("GET"))
		.enrich("direct:getTrains")
		.log("EXCHANGE: ${body}")
		.to("direct:forward");
	}

}
