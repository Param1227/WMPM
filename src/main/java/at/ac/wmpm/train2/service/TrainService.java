package at.ac.wmpm.train2.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.booking.model.TrainTicket;
import at.ac.wmpm.train1.service.Train1Respository;
import at.ac.wmpm.train2.service.Train2Respository;

public class TrainService {

	public List<Offer> getRides(String from, String to, Long date) {

		List<Offer> offers = Train2Respository.getOffers(from, to, new Date(date));

		return offers;
	}

	public TrainTicket bookTicket(String offerId) throws Exception {

		TrainTicket ticket = Train1Respository.bookTicket(UUID.fromString(offerId));

		return ticket;
	}
	
}
