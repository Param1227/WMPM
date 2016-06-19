package at.ac.wmpm.trainbooking.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestNotFoundError implements Processor{

	@Override
	public void process(Exchange exc) throws Exception {
		// TODO Auto-generated method stub
		
		exc.getOut().setBody("No Trains available");
		exc.getOut().setHeader(Exchange.CONTENT_TYPE, "text/plain");
		exc.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
	}

}
