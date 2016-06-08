package at.ac.wmpm.train1.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.processor.PreparePathRequestProcessor;

public class BookingRouteBuilder extends RouteBuilder{

	private static final Logger LOG = LoggerFactory.getLogger(PreparePathRequestProcessor.class);
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		LOG.info("configure of Bookingroutebuilder");
		
		from("restlet:http://localhost:8081/booking/{offerId}")
		.removeHeaders("CamelHttp*")
//		.to("xy")
		.marshal("pojo2json")
		.unmarshal("xmljson");
		
	}

}
