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

import at.ac.wmpm.booking.model.Category;
import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.booking.model.Ride;
import at.ac.wmpm.booking.model.Seat;
import at.ac.wmpm.train1.service.Train1Respository;

public class Train2Respository {
	private static final Logger LOG = LoggerFactory.getLogger(Train2Respository.class);
	
	private static HashMap<Ride, List<Seat>> rides;

	public static void initialize() {
		LOG.trace("Ich komme in Train2Repository");
		if(rides == null) {
			rides = new HashMap<Ride, List<Seat>>();


			List<Ride> ridesToBeAdded = new ArrayList<Ride>();

			ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2015, 6, 1, 8, 43).getTime(), 610));
			ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2015, 6, 3, 8, 42).getTime(), 620));
			ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2015, 6, 2, 17, 13).getTime(), 630));
			ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2015, 6, 2, 17, 12).getTime(), 640));

			for(Ride ride:ridesToBeAdded) {
				List<Seat> seats = new ArrayList<Seat>();
				System.out.println("FIRST FOR");
				seats.add(new Seat(Category.FIRST, randomizePrice(new BigDecimal(ride.getDuration()*10)), true));
				seats.add(new Seat(Category.FIRST, randomizePrice(new BigDecimal(ride.getDuration()*6)), true));

				rides.put(ride,seats);
			}
		}
	}
	
	private static BigDecimal randomizePrice(BigDecimal price) {
		BigDecimal min = price.multiply(new BigDecimal(0.9));
		BigDecimal max = price.multiply(new BigDecimal(1.1));
		BigDecimal range = max.subtract(min); 
		return min.add(range.multiply(new BigDecimal(Math.random()))); 
	}

	public static List<Offer> getOffers(String from, String to, Date date) {
		// TODO Auto-generated method stub
		initialize();

		List<Offer> offers = new ArrayList<Offer>();

		for(Ride ride:rides.keySet()) {
			System.out.println("SECOND FOR");
			if(ride.getFrom().equals(from) && ride.getTo().equals(to)) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				calendar.add(Calendar.DATE, -1);
				Date minusOne = calendar.getTime();

				calendar.add(Calendar.DATE, 2); 
				Date plusOne = calendar.getTime();

				if(ride.getDate().before(plusOne) && ride.getDate().after(minusOne)) {

					for(Seat seat:rides.get(ride)) {
						System.out.println("THIRD FOR");
						
						Offer offer = convertRideToOffer(ride, seat);
						
						offer.setId(UUID.randomUUID());
						offers.add(offer);	
					}
				}
			}
		}
		System.out.println("HERE I DONT HAVE AN EXCEPTION");
		return offers;
	}
	public static Offer convertRideToOffer(Ride ride, Seat seat) {

		return new Offer(ride.getFrom(), ride.getTo(), ride.getDate(), ride.getDuration(), seat.getCategory(), seat.getPrice());
	}


}