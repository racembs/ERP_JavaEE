package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Booking implements Serializable {
private static final long serialVersionUID = 1L;


	@Column(name = "BOOK_DATE")
	@Temporal(TemporalType.DATE)
	private Date BookDate;
	@Column(name = "Planned_Release_DATE")
	@Temporal(TemporalType.DATE)
	private Date PReleaseDate;
	@Column(name = "Actual_Release_DATE")
	@Temporal(TemporalType.DATE)
	private Date AReleaseDate;
	
	@EmbeddedId
	private BookingPK bookingPK;
	

	
	////////////////////
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="id_user",referencedColumnName="id_user",insertable=false,updatable=false),
			@JoinColumn(name="id_equipement",referencedColumnName="id_equipement",insertable=false,updatable=false)})
	private Works works;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_Need",referencedColumnName="id_Need",insertable=false,updatable=false)
	private Need need;



	public Date getBookDate() {
		return BookDate;
	}



	public void setBookDate(Date bookDate) {
		BookDate = bookDate;
	}


	public BookingPK getBookingPK() {
		return bookingPK;
	}



	public void setBookingPK(BookingPK bookingPK) {
		this.bookingPK = bookingPK;
	}



	public Works getWorks() {
		return works;
	}



	public void setWorks(Works works) {
		this.works = works;
	}



	public Need getNeed() {
		return need;
	}



	public void setNeed(Need need) {
		this.need = need;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Booking(Date bookDate, Date pReleaseDate, BookingPK bookingPK, Works works, Need need) {
		super();
		BookDate = bookDate;
		PReleaseDate = pReleaseDate;
		this.bookingPK = bookingPK;
		this.works = works;
		this.need = need;
	}



	public Date getPReleaseDate() {
		return PReleaseDate;
	}



	public void setPReleaseDate(Date pReleaseDate) {
		PReleaseDate = pReleaseDate;
	}



	public Date getAReleaseDate() {
		return AReleaseDate;
	}



	public void setAReleaseDate(Date aReleaseDate) {
		AReleaseDate = aReleaseDate;
	}



	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
