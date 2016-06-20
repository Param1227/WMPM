package at.ac.wmpm.trainbooking.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StornoMailProcessor implements Processor{

	private static final Logger LOG = LoggerFactory.getLogger(StornoMailProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> mail = exchange.getIn().getHeaders();

		String[] split = mail.get("Subject").toString().split(" ");

		LOG.info("Part 0 " + split[0].toString());
		LOG.info("Part 1 " + split[1].toString());
		if(split.length!=0) {
			String id = split[1];
			LOG.info("ID = " + id);
			//Object storno = exchange.getIn().getBody(String.class);

			if(split[0].toLowerCase().equals("storno")) {
				exchange.getOut().setBody(id);
			}
			else{
				exchange.getOut().setBody("Storno could not be done!");
			}
		}
		else{
			exchange.getOut().setBody("Content is wrong!");
		}
	}
}
