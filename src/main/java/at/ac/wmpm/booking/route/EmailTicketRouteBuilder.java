package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;

import at.ac.wmpm.booking.processor.CreateMailTicket;

import at.ac.wmpm.booking.util.GmailAccountProvider;

public class EmailTicketRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	//PersistTicket persist = new PersistTicket();
    	//TwitterPost twitter = new TwitterPost();
    	CreateMailTicket mail = new CreateMailTicket();
        
		//from("direct:persistTicket").process(persist)
		//	.to("mongodb:mongoDB?database=booking&collection=tickets&operation=insert");
        
		//from("direct:twitterTicket").process(twitter).to(AccountProvider.TWTR_URI);
        
		from("direct:emailTicket").process(mail).to(GmailAccountProvider.GMAIL_URI);
    }

}