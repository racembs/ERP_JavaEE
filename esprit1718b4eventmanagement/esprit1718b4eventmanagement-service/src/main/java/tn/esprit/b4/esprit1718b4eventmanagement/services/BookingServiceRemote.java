package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;


import tn.esprit.b4.esprit1718b4eventmanagement.entities.Booking;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.BookingPK;


@Remote
public interface BookingServiceRemote {
	public void addBooking(Booking booking);
	public void deleteBooking(int idBooking);
	public void updateBooking(Booking booking);
	public Booking findBooking(int idBooking);
}
