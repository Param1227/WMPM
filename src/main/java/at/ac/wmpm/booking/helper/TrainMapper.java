package at.ac.wmpm.booking.helper;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.booking.model.Booking;
import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.train1.service.Train1Respository;

public class TrainMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(Train1Respository.class);


	public static HashMap<UUID, String> mapper;

	public TrainMapper() {
	}

	public static void initialize() {

		if(mapper == null) {
			mapper = new HashMap<UUID, String>();
		}
	}

	public static void registerOffer(Offer offer, String trainProvider) {
		System.out.println("HERE IS THE EXCEPTION NOT 2");
		LOG.debug("HERE IS THE EXCEPTION NOT 2");
		
		initialize();
		mapper.put(offer.getId(), trainProvider);
	}
	
	public static String getTrainForOffer(Offer offer) {
		
		return mapper.get(offer.getId());
	}

	public static String getTrainForBooking(Booking booking) {

		return mapper.get(booking.getId());
	}
}
