package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.booking.service.AggregationStrategyWeather;


public class AddWeatherRouteBuilder extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        from("direct:addWeather")
        .choice()
        .when(simple("${header.to} == 'VIE'")).enrich("weather:weather?location=Vienna,at&period=7", new AggregationStrategyWeather())
        .otherwise()
        .when(simple("${header.to} == 'BER'")).enrich("weather:weather?location=Berlin,gr&period=7", new AggregationStrategyWeather())
        .otherwise()
        .when(simple("${header.to} == 'CPH'")).enrich("weather:weather?location=Copenhagen,dk&period=7", new AggregationStrategyWeather())
        .otherwise()
        .when(simple("${header.to} == 'CDG'")).enrich("weather:weather?location=Paris,fr&period=7", new AggregationStrategyWeather())
        .otherwise()
        .when(simple("${header.to} == 'ROM'")).enrich("weather:weather?location=Rome,it&period=7", new AggregationStrategyWeather());
    }
    
}