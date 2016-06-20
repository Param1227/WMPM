package at.ac.wmpm.trainbooking.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import at.ac.wmpm.trainbooking.model.TestBean;

//works as a message translator 
public class SaveTestBean implements Processor{

	//we get the input to be processed as an Exchange object
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		//we extract the input as an object of the class TestBean
		TestBean testbean = exchange.getIn().getBody(TestBean.class);
		
		//we write the object testbean out
		exchange.getOut().setBody(testbean);
	}

}
