package at.ac.wmpm.train1.route;

import org.apache.camel.builder.RouteBuilder;

public class Train1OfferRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("restlet:http://localhost:8081/off/{date}/{from}/{to}")
		.removeHeaders("CamelHttp*")
		.log("Hier komme ich hin 9")
		.to("bean:train1Service?method=getRides(${header.date}, ${header.from}, ${header.to})")
		.marshal("pojo2json")
    	.unmarshal("xmljson")
    	.log("Train1.2 response: ${body}");
	}

}
