package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MvtApprov")
public class MvtApprov  implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ; 
	
	@ManyToOne
	private Article article;
	
	@ManyToOne
	private SpareParts spareparts;
	
	private int quantity;
	
	@Temporal(TemporalType.DATE)
	private Date alarmDate;
	
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	@Temporal(TemporalType.DATE)
	private Date receptionDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public SpareParts getSpareparts() {
		return spareparts;
	}
	public void setSpareparts(SpareParts spareparts) {
		this.spareparts = spareparts;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getAlarmDate() {
		return alarmDate;
	}
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getReceptionDate() {
		return receptionDate;
	}
	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}
	public MvtApprov(Article article, SpareParts spareparts, int quantity, Date alarmDate, Date requestDate,
			Date receptionDate) {
		super();
		this.article = article;
		this.spareparts = spareparts;
		this.quantity = quantity;
		this.alarmDate = alarmDate;
		this.requestDate = requestDate;
		this.receptionDate = receptionDate;
	}
	public MvtApprov() {
		super();
	}
	
	
}
