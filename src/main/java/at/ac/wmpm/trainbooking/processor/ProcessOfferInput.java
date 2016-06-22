package at.ac.wmpm.trainbooking.processor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class ProcessOfferInput implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
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

	

}
