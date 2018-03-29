package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;


import tn.esprit.b4.esprit1718b4eventmanagement.entities.Booking;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.BookingPK;

@Local
public interface BookingServiceLocal {
	public void addBooking(Booking booking);
	public void deleteBooking(int idBooking);
	public void updateBooking(Booking booking);
	public Booking findBooking(int idTool, int id_Work);

}
