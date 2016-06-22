package at.ac.wmpm.trainbooking.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import at.ac.wmpm.trainbooking.model.StornoID;


public class StornoProcessor implements Processor{
	
	private static final Logger log = LoggerFactory.getLogger(StornoProcessor.class);
	
	private Mongo mongoObject;
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		StornoID id = exchange.getIn().getBody(StornoID.class);
		log.info("ID in Storno: "+id.getId());
		
		this.stornoTicket(id.getId());
		
		String str = "The ticket Storno was successfully ";
		exchange.getOut().setBody(str);

		
	}
	
	public void stornoTicket(String id) {
		log.info("ID in Storno: "+id);
		DBCollection dbCol = mongoObject.getDB("trainbooking").getCollectionFromString("tickets");
		log.info("Mongo DBCollection: "+ dbCol);
		//id="ed504159-1fdb-4d06-a1d1-224967de01ca";
		BasicDBObject basicDBObject = new BasicDBObject("id",id);
        DBObject dbObj = dbCol.findOne(basicDBObject);
        
		log.info("Mongo dbObj:  "+ dbObj);
		
		BasicDBObject updateObject = new BasicDBObject("id",id);
		updateObject.append("Storno", true);
        
		dbCol.update(dbObj,updateObject);
		
	}

    public void setMongo(Mongo mongo) {
        this.mongoObject = mongo;
    }
	

}
