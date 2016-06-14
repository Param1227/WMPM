package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;


public class WeatherRouteBuilder extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
    	from("direct:weatherProducer").to("weather:weather?location=Madrid,Spain&period=7");
    }
    
}