package at.ac.wmpm.train1.route;

import org.apache.camel.builder.RouteBuilder;

public class BookingRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("restlet:http://localhost:8081/booking/{offerId}")
		.removeHeaders("CamelHttp*")
		.log("Hier 2")
		.to("")
		.marshal("pojo2json")
		.unmarshal("xml2json");
		
	}

}
