package at.ac.wmpm.trainbooking.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;

import at.ac.wmpm.trainbooking.processor.MyPrepareProcessor;
import at.ac.wmpm.trainbooking.processor.PersistTicket;
import at.ac.wmpm.trainbooking.processor.TwitterProcessor;

public class MulticastRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	errorHandler(deadLetterChannel("jms:queue:dead")
			    .maximumRedeliveries(3).redeliveryDelay(1000).onPrepareFailure(new MyPrepareProcessor()));
    	
    	PersistTicket persist = new PersistTicket();
    	TwitterProcessor twitter = new TwitterProcessor();
        
		from("direct:persistTicket").process(persist)
			.to("mongodb:mongoDB?database=trainbooking&collection=tickets&operation=insert");

		   TwitterComponent tc = getContext().getComponent("twitter",
	               TwitterComponent.class);
	       tc.setAccessToken(TwitterAccount.TWITTER_ACCESSTOKEN);
	       tc.setAccessTokenSecret(TwitterAccount.TWITTER_ACCESSTOKENSECRET);
	       tc.setConsumerKey(TwitterAccount.TWITTER_CONSUMERKEY);
	       tc.setConsumerSecret(TwitterAccount.TWITTER_CONSUMERSECRET);
	       from("direct:twitterTicket").process(twitter).to("twitter://timeline/user");
	       
	//	from("direct:twitterTicket").process(twitter).to("twitter://timeline/user");
		
    }

}