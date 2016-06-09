package at.ac.wmpm.booking.helper;

import java.util.HashMap;
import java.util.UUID;

import at.ac.wmpm.booking.model.Booking;
import at.ac.wmpm.booking.model.Customer;

public class CustomerDirectory {

	
	private static HashMap<UUID, Customer> customers = new HashMap<UUID, Customer>();
	
	public static void saveCustomer(Booking booking) {
		// TODO Auto-generated method stub
		customers.put(booking.getId(), new Customer(booking.getName(), booking.getEmail()));
	}
	
	public static Customer getCustomer(UUID id) {
		
		return customers.get(id);
	}
}
