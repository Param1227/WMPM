package at.ac.wmpm.train2.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import at.ac.wmpm.booking.model.Category;
import at.ac.wmpm.booking.model.Offer;
import at.ac.wmpm.booking.model.Ride;
import at.ac.wmpm.booking.model.Seat;

public class Train2Respository {
private static HashMap<Ride, List<Seat>> rides;
	
	public static void initialize() {

		if(rides == null) {
			rides = new HashMap<Ride, List<Seat>>();
		}
		
		List<Ride> ridesToBeAdded = new ArrayList<Ride>();
		
		ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2015, 6, 1, 8, 43).getTime(), 654));
		ridesToBeAdded.add(new Ride("ROM", "VIE", new GregorianCalendar(2015, 6, 3, 8, 42).getTime(), 654));
		ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2015, 6, 2, 17, 13).getTime(), 645));
		ridesToBeAdded.add(new Ride("VIE", "ROM", new GregorianCalendar(2015, 6, 2, 17, 12).getTime(), 645));
		
		for(Ride ride:ridesToBeAdded) {
			List<Seat> seats = new ArrayList<Seat>();
			
			seats.add(new Seat(Category.FIRST, new BigDecimal(ride.getDuration()*10), true));
			seats.add(new Seat(Category.FIRST, new BigDecimal(ride.getDuration()*6), true));
			
			rides.put(ride,seats);
		}
	}

	public static List<Offer> getOffers(String from, String to, Date date) {
		// TODO Auto-generated method stub
		initialize();
		
		List<Offer> offers = new ArrayList<Offer>();
		
		for(Ride ride:rides.keySet()) {
			if(ride.getFrom().equals(from) && ride.getTo().equals(to)) {
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				calendar.add(Calendar.DATE, -1);
				Date minusOne = calendar.getTime();
				
				calendar.add(Calendar.DATE, 2); 
				Date plusOne = calendar.getTime();

				if(ride.getDate().before(plusOne) && ride.getDate().after(minusOne)) {
					
					for(Seat seat:rides.get(ride)) {
						Offer offer = convertRideToOffer(ride, seat);
						
						offer.setId(UUID.randomUUID());
						offers.add(offer);	
					}
				}
			}
		}
		
		return offers;
	}
	 public static Offer convertRideToOffer(Ride ride, Seat seat) {

	        return new Offer(ride.getFrom(), ride.getTo(), ride.getDate(), ride.getDuration(), seat.getCategory(), seat.getPrice());
	    }

	
}