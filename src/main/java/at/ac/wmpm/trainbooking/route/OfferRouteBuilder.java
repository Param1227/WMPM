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
		.process(new Processor() {
			public void process(Exchange exchange) throws ParseException {
				try {
					Message in = exchange.getIn();
					String time = (String) in.getHeader("date");
					Calendar calender = new GregorianCalendar();
					Date nDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(time);
					calender.setTime(nDate);
					in.setHeader("date", calender.getTimeInMillis());
					exchange.getIn().setBody("date was correctly transformed");
				} catch (Exception e) {
					exchange.getIn().setBody(null);
				}
			}
		})
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
		.log("Hier komme ich hin 2")
		.to("direct:prepareResponse");
	}

}
