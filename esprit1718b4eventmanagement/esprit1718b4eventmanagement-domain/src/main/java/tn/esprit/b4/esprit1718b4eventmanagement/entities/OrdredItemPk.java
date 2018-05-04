package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdredItemPk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_Article",nullable=true)
	private Integer id_Article;
	@Column(name="id_Order",nullable=true)
	private Integer id_Order;
	
	public OrdredItemPk() {
	}
	
	public OrdredItemPk(Integer id_Article, Integer id_Order) {
		this.id_Article = id_Article;
		this.id_Order = id_Order;
	}
	public Integer getId_Article() {
		return id_Article;
	}
	public Integer getId_Order() {
		return id_Order;
	}
	public void setId_Order(Integer id_Order) {
		this.id_Order = id_Order;
	}
	public void setId_Article(Integer id_Article) {
		this.id_Article = id_Article;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_Article == null) ? 0 : id_Article.hashCode());
		result = prime * result + ((id_Order == null) ? 0 : id_Order.hashCode());
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
		OrdredItemPk other = (OrdredItemPk) obj;
		if (id_Article == null) {
			if (other.id_Article != null)
				return false;
		} else if (!id_Article.equals(other.id_Article))
			return false;
		if (id_Order == null) {
			if (other.id_Order != null)
				return false;
		} else if (!id_Order.equals(other.id_Order))
			return false;
		return true;
	}
	
}
