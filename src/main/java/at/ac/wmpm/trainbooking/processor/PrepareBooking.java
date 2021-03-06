package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.trainbooking.directory.CustomerMap;
import at.ac.wmpm.trainbooking.directory.TrainMapper;
import at.ac.wmpm.trainbooking.model.Booking;

public class PrepareBooking implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(PrepareBooking.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		Booking booking = exchange.getIn().getBody(Booking.class);
		
		CustomerMap.saveCustomer(booking);
		
		String train = TrainMapper.getTrainForBooking(booking);
		
		LOG.info("Booking processor: "+train);
		
		exchange.getOut().setHeader("train", train);
		exchange.getOut().setHeader("bookingId", booking.getId().toString());
		exchange.getOut().setBody(null);
	}

}
