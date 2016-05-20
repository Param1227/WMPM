package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class AppRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		// servlet configuration
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
		.dataFormatProperty("prettyPrint", "true")
		.port(8080);

		
		// OFFER
		rest("offer").description("Get offers")
		.consumes("application/json").produces("application/json")

		.get("{from}/{to}").description("Get offers. Try for example: 1435795200000")
		.to("direct:processInput");

	}

}
