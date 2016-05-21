package at.ac.wmpm.train2.service;

import java.util.Date;
import java.util.List;

import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.train2.service.Train2Respository;

public class TrainService {

public List<Offer> getRides(String from, String to, Long date) {
		
		List<Offer> offers = Train2Respository.getOffers(from, to, new Date(date));
		
		return offers;
	}
}
