package at.ac.wmpm.train2.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.wmpm.train1.service.Train1Respository;
import at.ac.wmpm.trainbooking.model.Category;
import at.ac.wmpm.trainbooking.model.Offer;
import at.ac.wmpm.trainbooking.model.Ride;
import at.ac.wmpm.trainbooking.model.Seat;
import at.ac.wmpm.trainbooking.model.TrainTicket;

public class Train2Respository {
	private static final Logger LOG = LoggerFactory.getLogger(Train2Respository.class);
	
	private static HashMap<Ride, List<Seat>> rides;
	private static HashMap<UUID, UUID> offer2ride;
	private static HashMap<UUID, UUID> offer2seat;

	public static void initialize() {
		LOG.trace("Ich komme in Train2Repository");
		if(rides == null) {
			rides = new HashMap<Ride, List<Seat>>();
			offer2seat = new HashMap<UUID, UUID>();
			offer2ride = new HashMap<UUID, UUID>();

			List<Ride> ridesToBeAdded = new ArrayList<Ride>();

			ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2016, 7, 1, 8, 43).getTime(), 610));
			ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2016, 7, 2, 8, 42).getTime(), 620));
			ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2016, 7, 2, 17, 13).getTime(), 630));
			ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2016, 7, 2, 17, 12).getTime(), 640));

			for(Ride ride:ridesToBeAdded) {
				List<Seat> seats = new ArrayList<Seat>();
				
				seats.add(new Seat(Category.FIRST, generatePrice(new BigDecimal(ride.getDuration()*10)), true));
				seats.add(new Seat(Category.ECONOMY, generatePrice(new BigDecimal(ride.getDuration()*6)), true));

				rides.put(ride,seats);
			}
		}
	}

	public static List<Offer> getOffers(String f, String t, Date d) {
		initialize();

		List<Offer> offers = new ArrayList<Offer>();

		for(Ride ride:rides.keySet()) {
			System.out.println("SECOND FOR");
			if(ride.getFrom().equals(f) && ride.getTo().equals(t)) {

				Calendar c = Calendar.getInstance();
				c.setTime(d);
				
				c.add(Calendar.DATE, 2); 
				Date plus = c.getTime();

				c.add(Calendar.DATE, -2);
				Date minus = c.getTime();

				if(ride.getDate().before(plus) && ride.getDate().after(minus)) {

					for(Seat seat:rides.get(ride)) {
						System.out.println("THIRD FOR");
						
						Offer offer = convertRideToOffer(ride, seat);
						
						offer.setId(UUID.randomUUID());
						offers.add(offer);	
						
						offer2seat.put(offer.getId(), seat.getId());
						offer2ride.put(offer.getId(), ride.getId());
					}
				}
			}
		}
		System.out.println("HERE I DONT HAVE AN EXCEPTION");
		return offers;
	}
	
	public static TrainTicket bookTicket(UUID id) {

		LOG.info("Komme in Repository 2");
		initialize();
		
		if(offer2seat.isEmpty() || offer2ride.isEmpty()) {
			return null;
		}

		UUID seatId = offer2seat.get(id);
		UUID rideId = offer2ride.get(id);
		
		if(seatId == null || rideId == null) {
			return null;
		}
		
		Ride ride = null;
		
		for(Ride r : rides.keySet()) {
			if(r.getId().equals(rideId)) {
				ride = r;
				break;
			}
		}
		
		Seat seat = null;
		
		for(Seat s : rides.get(ride)) {
			if(s.getId().equals(seatId)) {
				seat = s;
				break;
			}
		}
		

		if(seat.isBooked()) {
			LOG.info("is booked");
			return null;
		}
		
		rides.get(ride).remove(seat);
		seat.setBooked(true);
		rides.get(ride).add(seat);
		
		TrainTicket ticket = new TrainTicket();
		ticket.setId(id);
		ticket.setFrom(ride.getFrom());
		ticket.setTo(ride.getTo());
		ticket.setDate(ride.getDate());
		ticket.setCategory(seat.getCategory());
		ticket.setPrice(seat.getPrice());
		
		return ticket;
	}
	
	private static BigDecimal generatePrice(BigDecimal p) {
		BigDecimal maximum = p.multiply(new BigDecimal(1.4));
		BigDecimal minimum = p.multiply(new BigDecimal(1.2));
		BigDecimal range = maximum.subtract(minimum); 
		return minimum.add(range.multiply(new BigDecimal(Math.random()))); 
	}
	
	public static Offer convertRideToOffer(Ride ride, Seat seat) {

		return new Offer(ride.getFrom(), ride.getTo(), ride.getDate(), ride.getDuration(), seat.getCategory(), seat.getPrice());
	}


}