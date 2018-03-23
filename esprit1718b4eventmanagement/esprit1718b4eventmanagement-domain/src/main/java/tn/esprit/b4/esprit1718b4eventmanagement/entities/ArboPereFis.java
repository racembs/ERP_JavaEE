package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "tab_ArboPereFis")
public class ArboPereFis implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private ArboPereFisPk arboperefilsPk;
	
	@ManyToOne
	@JoinColumn(name="idArboFils",referencedColumnName="Id"
	,insertable=true,updatable=true)
	private Arboresence arboFils;
	
	@ManyToOne
	@JoinColumn(name="idArboPere",referencedColumnName="Id"
	,insertable=true,updatable=true)
	private  Arboresence arboPere;

	public ArboPereFisPk getArboperefilsPk() {
		return arboperefilsPk;
	}

	public void setArboperefilsPk(ArboPereFisPk arboperefilsPk) {
		this.arboperefilsPk = arboperefilsPk;
	}

	public Arboresence getArboFils() {
		return arboFils;
	}

	public void setArboFils(Arboresence arboFils) {
		this.arboFils = arboFils;
	}

	public Arboresence getArboPere() {
		return arboPere;
	}



	
	/** serialVersionUID */
	







}