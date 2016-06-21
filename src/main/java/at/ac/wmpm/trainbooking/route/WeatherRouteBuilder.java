package at.ac.wmpm.trainbooking.route;

import java.text.ParseException;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import at.ac.wmpm.trainbooking.processor.MissingInputError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherRouteBuilder extends RouteBuilder {
	
	
	 private static final Logger LOG = LoggerFactory.getLogger(WeatherRouteBuilder.class);

	
		@Override
		public void configure() throws Exception {
			
			from("direct:processWeatherInput")
			.process(new Processor() {
				public void process(Exchange exchange) throws ParseException {
					try {
						Message in = exchange.getIn();
						String to = (String) in.getHeader("to");
						LOG.info("this is the output for to: "+to); 
						exchange.getIn().setBody("The body can be further processed");
						} catch (Exception e) {
						exchange.getIn().setBody(null);
					}
				}
			})
			.choice()
			.when(simple("${body} == null")).bean(new MissingInputError(), "process")
			.otherwise()
			.log("this is the header to: ${header.to}")
			.setBody().simple("{\"city\": \"${header.to}\" }")
			.enrich("mongodb:mongoDB?database=trainbooking&collection=weather&operation=findOneByQuery")
			.choice()
			.when(simple("${body} == null")).bean(new MissingInputError(), "process")
			.otherwise()
			.log("Weather is herer!");
			//.to("direct:prepareWeatherResponse");
		}

	}
