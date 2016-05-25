package at.ac.wmpm.train2.route;

import org.apache.camel.builder.RouteBuilder;

public class Train2OfferRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		from("restlet:http://localhost:8082/req/{date}/{from}/{to}")
		.to("bean:train2Service?method=getRides(${header.from}, ${header.to}, ${header.date})");
		//.marshal("pojo2json");
	}

}
