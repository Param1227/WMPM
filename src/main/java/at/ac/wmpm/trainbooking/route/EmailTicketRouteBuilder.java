package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.trainbooking.processor.CreateMailTicket;

import at.ac.wmpm.trainbooking.accounts.GmailAccountProvider;;

public class EmailTicketRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        	
    	CreateMailTicket mail = new CreateMailTicket();
        
        from("direct:emailTicket").process(mail).to(GmailAccountProvider.GMAIL_URI);
    }

}