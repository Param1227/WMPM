package at.ac.wmpm.train1.route;

import org.apache.camel.builder.RouteBuilder;

public class Train1OfferRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("restlet:http://localhost:8081/req/{date}/{from}/{to}")
		.log("Hier komme ich hin 9")
		.to("bean:train1Service?method=getRides(${header.from}, ${header.to}, ${header.date})")
		.marshal("pojo2json")
    	.unmarshal("xmljson")
    	.log("Train1 response: ${body}");
	}

}
