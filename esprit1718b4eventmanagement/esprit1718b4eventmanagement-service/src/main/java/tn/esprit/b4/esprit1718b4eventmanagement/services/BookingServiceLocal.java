package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Booking;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.BookingPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;

@Local
public interface BookingServiceLocal {
	public BookingPK addBooking(int idOperatingRange, WorksPK WorkPK, Booking booking);
	public void deleteBooking(int idTool, WorksPK WorkPK);
	public void updateBooking(Booking booking);
	public Booking findBooking(int idTool, WorksPK WorkPK);

}
