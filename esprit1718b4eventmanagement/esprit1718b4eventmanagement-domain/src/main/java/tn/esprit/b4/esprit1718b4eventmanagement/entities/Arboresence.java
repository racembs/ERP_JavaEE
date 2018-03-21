package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "tab_Arboresence")
public class Arboresence implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "name")
	private String name;
	
	@JoinTable(name = "Parent_Child_Tree", joinColumns = {
			 @JoinColumn(name = "Parent", referencedColumnName = "Id", nullable = false)}, inverseJoinColumns = {
			 @JoinColumn(name = "Child", referencedColumnName = "Id", nullable = false)})
	@ManyToMany
	private List<Arboresence> parent = new ArrayList<>();
	
	@ManyToMany(mappedBy = "parent")
    private List<Arboresence> child;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Arboresence> getParent() {
		return parent;
	}

	public void setParent(List<Arboresence> parent) {
		this.parent = parent;
	}

	public List<Arboresence> getChild() {
		return child;
	}

	public void setChild(List<Arboresence> child) {
		this.child = child;
	}
	
	
	
	
	
	
	

}