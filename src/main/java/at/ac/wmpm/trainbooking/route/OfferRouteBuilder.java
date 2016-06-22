package at.ac.wmpm.trainbooking.route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.trainbooking.processor.MissingInputError;
import at.ac.wmpm.trainbooking.processor.MyPrepareProcessor;

public class OfferRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		errorHandler(deadLetterChannel("jms:queue:dead")
			    .maximumRedeliveries(3).redeliveryDelay(1000).onPrepareFailure(new MyPrepareProcessor()));
		
		from("direct:processInput")
		.process("processOfferInput")
		.choice()
		.when(simple("${body} == null")).bean(new MissingInputError(), "process")
		.otherwise()
		.setBody().simple("{ \"id\": \"${header.from}\" }")
		.enrich("mongodb:mongoDB?database=trainbooking&collection=trainstations&operation=findOneByQuery")
		.choice()
		.when(simple("${body} == null")).bean(new MissingInputError(), "process")
		.otherwise()
		.setBody().simple("{ \"id\": \"${header.to}\" }")
		.enrich("mongodb:mongoDB?database=trainbooking&collection=trainstations&operation=findOneByQuery")
		.choice()
		.when(simple("${body} == null")).bean(new MissingInputError(), "process")
		.otherwise()
		.to("direct:prepareResponse");
	}

}
