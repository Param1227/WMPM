package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import at.ac.wmpm.booking.model.Booking;

public class AppRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		// servlet configuration
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
		.dataFormatProperty("prettyPrint", "true")
		.port(8080);

		// OFFER
		rest("/offer").description("Get offers")
		.consumes("application/json").produces("application/json")

		.get("/{date}/{from}/{to}").description("Get offers. Try for example:")
		.to("direct:processInput");

		// Booking
		rest("/booking").description("Book an offer")
		.consumes("application/json").produces("application/json")

		.post("/").description("Book an offer").type(Booking.class)
		.to("direct:processBooking");

	}

}
