package at.ac.wmpm.trainbooking.directory;

import java.util.HashMap;
import java.util.UUID;

import at.ac.wmpm.trainbooking.model.Booking;
import at.ac.wmpm.trainbooking.model.Customer;
import at.ac.wmpm.trainbooking.model.Ticket;
import at.ac.wmpm.trainbooking.model.TrainTicket;

public class TicketMap {
	
	private static HashMap<String, Ticket> tickets = new HashMap<String, Ticket>();
	
	public static Ticket getTicket(String uuid) {
		return tickets.get(uuid);
	}
	
	public static void saveTicket(Ticket t) {
		tickets.put(t.getId(), t);
	}
}
