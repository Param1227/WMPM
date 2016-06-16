package at.ac.wmpm.booking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;

import at.ac.wmpm.booking.processor.PersistTicket;
//import at.ac.wmpm.booking.processor.CreateTicketingMail;
//import at.ac.wmpm.booking.processor.PersistTicket;
import at.ac.wmpm.booking.processor.TwitterPost;
//import at.ac.wmpm.booking.util.AccountProvider;

public class TicketRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	PersistTicket persist = new PersistTicket();
    	TwitterPost twitter = new TwitterPost();
    	//CreateTicketingMail mail = new CreateTicketingMail();
        
		from("direct:persistTicket").process(persist)
			.to("mongodb:mongoDB?database=trainbooking&collection=tickets&operation=insert");
//		from("direct:emailTicket").process(mail).to(AccountProvider.GMAIL_URI);
		
    	
	//from("direct:twitterTicket").process(twitter).to(TwitterAccount.TWTR_URI);
		
		   TwitterComponent tc = getContext().getComponent("twitter",
	               TwitterComponent.class);
	       tc.setAccessToken(TwitterAccount.TWTR_ACCESSTOKEN);
	       tc.setAccessTokenSecret(TwitterAccount.TWTR_ACCESSTOKENSECRET);
	       tc.setConsumerKey(TwitterAccount.TWTR_CONSUMERKEY);
	       tc.setConsumerSecret(TwitterAccount.TWTR_CONSUMERSECRET);
	       from("direct:twitterTicket").process(twitter).to("twitter://timeline/user");
		
	//	from("direct:twitterTicket").process(twitter).to("twitter://timeline/user");
		
        
    }

}