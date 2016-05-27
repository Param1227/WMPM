package at.ac.wmpm.train1.service;

import java.util.Date;
import java.util.List;

import at.ac.wmpm.booking.model.Offer;

public class TrainService {
	
	public List<Offer> getRides(Long date, String from, String to) {
		
		List<Offer> offers = Train1Respository.getOffers(from, to, new Date(date));
		
		return offers;
	}
	
}
