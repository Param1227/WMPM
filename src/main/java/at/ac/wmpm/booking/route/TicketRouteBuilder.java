package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;

//import at.ac.wmpm.booking.processor.CreateTicketingMail;
//import at.ac.wmpm.booking.processor.PersistTicket;
import at.ac.wmpm.booking.processor.TwitterPost;
//import at.ac.wmpm.booking.util.AccountProvider;

public class TicketRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	//PersistTicket persist = new PersistTicket();
    	TwitterPost twitter = new TwitterPost();
    	//CreateTicketingMail mail = new CreateTicketingMail();
        
//		from("direct:persistTicket").process(persist)
//			.to("mongodb:mongoDB?database=booking&collection=tickets&operation=insert");
//		from("direct:emailTicket").process(mail).to(AccountProvider.GMAIL_URI);
		
    	
		from("direct:twitterTicket").process(twitter).to(TwitterAccount.TWTR_URI);
        
    }

}