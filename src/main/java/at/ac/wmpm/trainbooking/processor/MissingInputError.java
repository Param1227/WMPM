package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MissingInputError implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		exchange.getOut().setBody("Please choose one of the available destinations and a correct date!!");
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400); //bad request
		
	}

}
