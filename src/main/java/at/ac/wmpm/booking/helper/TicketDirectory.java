package at.ac.wmpm.booking.helper;

import java.util.HashMap;
import java.util.UUID;

import at.ac.wmpm.booking.model.Booking;
import at.ac.wmpm.booking.model.Customer;
import at.ac.wmpm.booking.model.Ticket;
import at.ac.wmpm.booking.model.TrainTicket;

public class TicketDirectory {

	
	private static HashMap<String, Ticket> tickets = new HashMap<String, Ticket>();
	
	public static void saveTicket(Ticket ticket) {

		
		tickets.put(ticket.getId(), ticket);
	}
	
	public static Ticket getTicket(String id) {
		
		return tickets.get(id);
	}
}
