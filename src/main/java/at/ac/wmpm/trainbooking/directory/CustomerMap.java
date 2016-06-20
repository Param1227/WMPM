package at.ac.wmpm.trainbooking.directory;

import java.util.HashMap;
import java.util.UUID;

import at.ac.wmpm.trainbooking.model.Booking;
import at.ac.wmpm.trainbooking.model.Customer;

public class CustomerMap {

	private static HashMap<UUID, Customer> c = new HashMap<UUID, Customer>();

	public static Customer getCustomer(UUID id) {

		return c.get(id);
	}

	public static void saveCustomer(Booking booking) {
		c.put(booking.getId(), new Customer(booking.getName(), booking.getEmail()));
	}
}
