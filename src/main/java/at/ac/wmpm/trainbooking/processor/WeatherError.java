package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class WeatherError implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		exchange.getOut().setBody("The weather is currently not available for the chosen destination");
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400); //bad request
		
	}

}