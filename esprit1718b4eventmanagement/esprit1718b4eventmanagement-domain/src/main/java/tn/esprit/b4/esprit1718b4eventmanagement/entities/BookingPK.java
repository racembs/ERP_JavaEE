package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class BookingPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="id_Works")
	private Integer id_works;
	
	@Column(name="id_Need")
	private Integer id_Need;

	public Integer getId_works() {
		return id_works;
	}

	public void setId_works(Integer id_works) {
		this.id_works = id_works;
	}

	public Integer getId_Need() {
		return id_Need;
	}

	public void setId_Need(Integer id_Need) {
		this.id_Need = id_Need;
	}

	public BookingPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingPK(Integer id_works, Integer id_Need) {
		super();
		this.id_works = id_works;
		this.id_Need = id_Need;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_Need == null) ? 0 : id_Need.hashCode());
		result = prime * result + ((id_works == null) ? 0 : id_works.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingPK other = (BookingPK) obj;
		if (id_Need == null) {
			if (other.id_Need != null)
				return false;
		} else if (!id_Need.equals(other.id_Need))
			return false;
		if (id_works == null) {
			if (other.id_works != null)
				return false;
		} else if (!id_works.equals(other.id_works))
			return false;
		return true;
	}
	
	

}
