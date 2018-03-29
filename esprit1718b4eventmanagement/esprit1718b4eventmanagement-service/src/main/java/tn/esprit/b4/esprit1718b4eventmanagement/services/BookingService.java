package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Booking;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.BookingPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;


public class BookingService implements BookingServiceLocal,BookingServiceRemote{
	@PersistenceContext(unitName="spotlight-ejb")
	EntityManager em;
	@Override
	public void addBooking( Booking booking) {
		em.persist(booking);
	}

	@Override
	public void deleteBooking(int idBooking) {
		Booking w=em.find(Booking.class,idBooking);
		em.remove(w);
		
	}

	@Override
	public void updateBooking(Booking booking) {
		em.merge(booking);
		
	}

	@Override
	public Booking findBooking(int idTool, int id_Work) {
		// TODO Auto-generated method stub
		return null;
	}

}
