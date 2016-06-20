package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import at.ac.wmpm.trainbooking.model.Booking;
import at.ac.wmpm.trainbooking.model.StornoID;
import at.ac.wmpm.trainbooking.model.Ticket;

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
		
		// Twitter
		rest("/twitter").description("Posts ticket to twitter").
		consumes("application/json").produces("application/json")
		.post("/").description("Ticket Twitter post").type(Ticket.class).to("direct:twitterTicket");
		
		
		//Persist
		rest("/persistTicket").description("Persist ticket into the database")
		.consumes("application/json").produces("application/json")
		.post("/").description("Persist ticket").type(Ticket.class)
		.to("direct:persistTicket");
		
		// Email
		rest("/email").description("Send the booked ticket").
		consumes("application/json").produces("application/json")
		.post("/").description("Email booked ticket").type(Ticket.class).to("direct:emailTicket");
		
		//MulticastTicketWrite
		//eMail, Twitter, Persist into Database
		rest("/multicastTicket").description("performs multicast")
		.consumes("application/json").produces("application/json")
		.post("/").description("Performs multicast: sends mail, posts and persists a ticket").type(Ticket.class)
		.to("direct:multicastTickets");

		
		//Ticket Storna
		rest("/stornoTicket").description("Cancel booked ticket")
		.consumes("application/json").produces("application/json")
		.post("/").description("Performs a cancellation of a ticket with is already booked").type(StornoID.class)
		.to("direct:storno");
		
	}

}
