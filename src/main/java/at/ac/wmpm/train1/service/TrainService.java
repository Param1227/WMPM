package at.ac.wmpm.train1.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.ac.wmpm.booking.model.TrainTicket;
import at.ac.wmpm.booking.model.Offer;

public class TrainService {
	
	public List<Offer> getRides(Long date, String from, String to) {
		
		List<Offer> offers = Train1Respository.getOffers(from, to, new Date(date));
		
		System.out.println("offer = "+offers.get(0).toString());
		
		return offers;
	}
	
	public TrainTicket bookTicket(String offerId) throws Exception {

		TrainTicket ticket = Train1Respository.bookTicket(UUID.fromString(offerId));

		return ticket;
	}
	
}
