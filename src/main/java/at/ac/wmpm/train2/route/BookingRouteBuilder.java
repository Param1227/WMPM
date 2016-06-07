package at.ac.wmpm.train2.route;

import org.apache.camel.builder.RouteBuilder;

public class BookingRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("restlet:http://localhost:8083/book/{offerId}")
		.removeHeaders("CamelHttp*")
		.log("Hier 1")
		.to("")
		.marshal("pojo2json")
		.unmarshal("xml2json");
		
	}

}
