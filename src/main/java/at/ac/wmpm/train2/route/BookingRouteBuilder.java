package at.ac.wmpm.train2.route;

import org.apache.camel.builder.RouteBuilder;

public class BookingRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("restlet:http://localhost:8082/book/{offerId}")
		.removeHeaders("CamelHttp*")
		.log("Hier 1")
		.to("bean:train2Service?method=bookTicket(${header.offerId})")
		.marshal("pojo2json")
		.unmarshal("xmljson");
		
	}

}
