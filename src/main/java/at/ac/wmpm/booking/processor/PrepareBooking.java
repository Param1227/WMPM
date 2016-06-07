package at.ac.wmpm.booking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import at.ac.wmpm.booking.helper.TrainMapper;
import at.ac.wmpm.booking.model.Booking;

public class PrepareBooking implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		Booking booking = exchange.getIn().getBody(Booking.class);
		
		String train = TrainMapper.getTrainForBooking(booking);
		
		exchange.getOut().setHeader("train", train);
		exchange.getOut().setHeader("bookingId", booking.getId().toString());
		exchange.getOut().setBody(null);
	}

}
