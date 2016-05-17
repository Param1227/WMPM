package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;
import at.ac.wmpm.booking.processor.MissingInputError;

public class OfferRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		
		from("direct:processInput")
//		.choice()
//		.when(simple("${body} == null")).bean(new MissingInputError(), "process")
//		.otherwise()
		.setBody().simple("{ \"id\": \"${header.from}\" }")
		.enrich("mongodb:mongoDB?database=trainbooking&collection=destinations&operation=findOneByQuery")
		.choice()
		.when(simple("${body} == null")).bean(new MissingInputError(), "process")
		.otherwise()
		.setBody().simple("{ \"id\": \"${header.to}\" }")
		.enrich("mongodb:mongoDB?database=trainbooking&collection=destinations&operation=findOneByQuery");
		
	}

}
