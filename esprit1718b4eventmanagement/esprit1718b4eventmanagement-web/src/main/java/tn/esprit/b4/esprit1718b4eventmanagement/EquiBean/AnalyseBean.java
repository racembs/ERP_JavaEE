package tn.esprit.b4.esprit1718b4eventmanagement.EquiBean;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.AnalyseServiceLocal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;



@ManagedBean
@ViewScoped
public class AnalyseBean  {
	/**
	 * 
	 */


	@EJB
	private AnalyseServiceLocal ServiceLocal;
	  @PostConstruct
	    public void init() throws NamingException {
		  analyse();
		  createPieModels();
	    }
	  
	  
	  List<UsualWork> cd;
	  private PieChartModel pieModel1;
	  private static Date datebeb;
	  private static Date datefin;
	  private static Long l;
	  private long nbequidispo;
	  
	  private long nbequipanne;

	
		
	  public void analyse()
	  {DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Calendar date = Calendar.getInstance();
   String  d = df.format(date.getTime());
		  nbequipanne=ServiceLocal.Date(d,d);
		  nbequidispo=ServiceLocal.count()-nbequipanne;
	
}
	  public void  redirection( Equipment equi)
	  {}
	
	  public void test()
	  {
	// l=ServiceLocal.nbrDayDate(datebeb,datefin);
		  l=ServiceLocal.MTTR(1);
}
	  
		
	  public List<UsualWork> test1()
	  {
	cd=ServiceLocal.ListWorks(1);
	return cd;
	
}
	  
public AnalyseServiceLocal getServiceLocal() {
	return ServiceLocal;
}


public void setServiceLocal(AnalyseServiceLocal serviceLocal) {
	ServiceLocal = serviceLocal;
}

public Date getDatebeb() {
	return datebeb;
}

public void setDatebeb(Date datebeb) {
	this.datebeb = datebeb;
}

public Date getDatefin() {
	return datefin;
}

public void setDatefin(Date datefin) {
	this.datefin = datefin;
}

public Long getL() {
	return l;
}

public void setL(Long l) {
	this.l = l;
}


public long getNbequidispo() {
	return nbequidispo;
}


public void setNbequidispo(long nbequidispo) {
	this.nbequidispo = nbequidispo;
}


public long getNbequipanne() {
	return nbequipanne;
}


public void setNbequipanne(long nbequipanne) {
	this.nbequipanne = nbequipanne;
}


public AnalyseBean() {
	super();
	// TODO Auto-generated constructor stub
}

public PieChartModel getPieModel1() {
	return pieModel1;
}

public void setPieModel1(PieChartModel pieModel1) {
	this.pieModel1 = pieModel1;
}
private void createPieModels() {
    createPieModel1();
}
 
private void createPieModel1() {
    pieModel1 = new PieChartModel();
     
    pieModel1.set("Brand 1", 540);
    pieModel1.set("Brand 2", 325);
    pieModel1.set("Brand 3", 702);
    pieModel1.set("Brand 4", 421);
     
    pieModel1.setTitle("Simple Pie");
    pieModel1.setLegendPosition("w");
}

public void itemSelect(ItemSelectEvent event) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                    "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
     
    FacesContext.getCurrentInstance().addMessage(null, msg);
}
}

