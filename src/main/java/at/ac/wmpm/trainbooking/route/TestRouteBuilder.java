package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.trainbooking.service.SaveTestBean;

// a class used in camel for smart routing within a camel context
public class TestRouteBuilder extends RouteBuilder{

	//This function builds the route
	@Override
	public void configure() throws Exception {
		
		// writes something into the database
		SaveTestBean savetestbean = new SaveTestBean();
		
		from("direct:saveTestBean").process(savetestbean)
			.to("mongodb:mongoDB?database=booking&collection=tickets&operation=insert");
	}

}
