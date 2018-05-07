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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.AnalyseServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceLocal;

import java.util.ArrayList;
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
	@EJB
	private EquipementServiceLocal equiServiceLocal;
	private Equipment equipement1;
	
	@PostConstruct
	    public void init() throws NamingException {
		  analyse();
		  createPieModels();
		  createAreaModel();
		  createBarModel();
	    }
	  
	 private BarChartModel barModel;
	static  List<UsualWork> cd =new ArrayList<>();
	 private LineChartModel areaModel;
      private PieChartModel pieModel1;
	  private static Date datebeb;
	  private static Date datefin;
	  private static Long l;
	  private long nbequidispo;
	  private static Equipment equipement;
	  private long nbequipanne;
	
	  
public static long MTTR;

public static long MTBF;

public static long NBpanne;
public static Integer avab;






public long nbrdipso()
{ nbequidispo=ServiceLocal.count()-nbequipanne;
	return nbequidispo;}






public long nbrpane()
{ DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
Calendar date = Calendar.getInstance();
String  d = df.format(date.getTime());
	  nbequipanne=ServiceLocal.Date(d,d);
	
	  
	  
	  return nbequipanne;}








public long nbrtot()

{ nbequidispo=ServiceLocal.count();
return nbequidispo;}
	  public void analyse()
	  {DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Calendar date = Calendar.getInstance();
   String  d = df.format(date.getTime());
		  nbequipanne=ServiceLocal.Date(d,d);
		  nbequidispo=ServiceLocal.count()-nbequipanne;
	}
	  
	  public String redirection( Equipment equi)
	  {   MTBF=0;
	  MTTR=0;
		  equipement=equi;
	  MTBF= ServiceLocal.MTBF(equi.getId());
		MTTR = ServiceLocal.MTTR(equi.getId());
avab=Integer.valueOf((int)(ServiceLocal.Availibitity(MTTR, MTBF)*100));


	  cd=ServiceLocal.ListWorks(equi.getId());
	  
	  return "/Equipement/EquipementAnalyse?faces-redirect=true";
	  }
	  
	  
	  
	  public String  EtatEquipement(Equipment equi)
	  {DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Calendar date = Calendar.getInstance();
   String  d = df.format(date.getTime());
   String Etat="";
		 long nb= ServiceLocal.Eat(d,d,equi.getId());
		 if (nb==0)
			 Etat="l'equipement"+equi.getSerialNum()+" est Disponible ";
		 else 
			 Etat="l'equipement"+equi.getSerialNum()+" est en panne ";
	
			
		 
		return Etat;}
	
	  
		public long nbrjourindi(Equipment equi)
		  {
			return ServiceLocal.DP(equi.getId());}
		
		public long nbheurindi(Equipment equi)
		  {
			return ServiceLocal.findChargingStationByEquipement(equi.getId()).getNbhours();}
	public long nbrheurtotalindi(Equipment equi)
	  {
		return nbheurindi(equi)*nbrjourindi(equi);}
	  
	  public Double cout(Equipment equi)
	  {
		return ServiceLocal.Cout(equi.getId());}
	  
	  public void test()
	  {
		Date date = new Date();
		
	 l=ServiceLocal.nbrDayDate(datebeb,date);
		 // l=ServiceLocal.MTTR(1);
}
	  
		
	  public List<UsualWork> test1()
	  {
	cd=ServiceLocal.ListWorks(equipement.getId());
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
     
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Calendar date = Calendar.getInstance();
 String  d = df.format(date.getTime());
    pieModel1.set("Equipement Disponible",ServiceLocal.count()-nbequipanne);
    pieModel1.set("Equipement en Panne", ServiceLocal.Date(d,d));
  
    pieModel1.setLegendPosition("r");
    
    pieModel1.setFill(true);
    pieModel1.setShowDataLabels(true);
    pieModel1.setDiameter(250);
}

public void itemSelect(ItemSelectEvent event) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                    "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
     
    FacesContext.getCurrentInstance().addMessage(null, msg);
}

public List<UsualWork> getCd() {
	return cd;
}
public void setCd(List<UsualWork> cd) {
	this.cd = cd;
}

public Equipment getEquipement1() {
		return equipement1;
	}
	public void setEquipement1(Equipment equipement1) {
		this.equipement1 = equipement1;
	}
	public Equipment getEquipement() {
		return equipement;
	}
	public void setEquipement(Equipment equipement) {
		this.equipement = equipement;
	}
	public long getMTTR() {
		return MTTR;
	}
	public void setMTTR(long mTTR) {
		MTTR = mTTR;
	}
	public long getMTBF() {
		return MTBF;
	}
	public void setMTBF(long mTBF) {
		MTBF = mTBF;
	}
	public long getNBpanne() {
		return NBpanne;
	}
	public void setNBpanne(long nBpanne) {
		NBpanne = nBpanne;
	}
		


	public  Integer getAvab() {
		return avab;
	}
	public void setAvab(Integer avab) {
		AnalyseBean.avab = avab;
	}
	public LineChartModel getAreaModel() {
		return areaModel;
	}
	public void setAreaModel(LineChartModel areaModel) {
		this.areaModel = areaModel;
	}
	
	  private void createAreaModel() {
	    List<Equipment> list=equiServiceLocal.getAllEquipment();
	        areaModel = new LineChartModel();

	        LineChartSeries minChart = new LineChartSeries();
	        minChart.setFill(true);
	        minChart.setLabel("Min");
	        for(int i=0 ;i<list.size();i++) {
	       	 minChart.set(list.get(i).getSerialNum()+
	       			 " ("+22+") "
	       			 , 25);
	        }
	        
	 
	        LineChartSeries maxChart = new LineChartSeries();
	        maxChart.setFill(true);
	        maxChart.setLabel("Normal");
	      
	        areaModel.addSeries(maxChart);
	        areaModel.addSeries(minChart);
	        
	 
	       // areaModel.setTitle("Orders Chart");
	      //  areaModel.setLegendPosition("ne");
	        areaModel.setStacked(true);
	        areaModel.setShowPointLabels(true);
	 
	        Axis xAxis = new CategoryAxis("Article(Nbre Of oders)");
	        areaModel.getAxes().put(AxisType.X, xAxis);
	        Axis yAxis = areaModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantity");
	        yAxis.setMin(0);
	        yAxis.setMax(600);
	    }
	  
	  

	    private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	 List<Equipment> list=equiServiceLocal.getAllEquipment();
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Equipement");
	        
	        for(Equipment e: list)
	        {
	    Long    MTTR1=ServiceLocal.MTTR(e.getId());
	    Long    MTBF1=ServiceLocal.MTBF(e.getId());
	        boys.set(e.getMarque()+":"+e.getSerialNum(),Integer.valueOf((int)(ServiceLocal.Availibitity(MTTR1, MTBF1)*100)));
	        }
	    
	 
	        model.addSeries(boys);
	       
	         
	        return model;
	    }
		public BarChartModel getBarModel() {
			return barModel;
		}
		public void setBarModel(BarChartModel barModel) {
			this.barModel = barModel;
		}
		  private void createBarModel() {
		        barModel = initBarModel();
		         
		        barModel.setTitle("Bar Chart");
		        barModel.setLegendPosition("ne");
		         
		        Axis xAxis = barModel.getAxis(AxisType.X);
		        xAxis.setLabel("Equipment");
		         
		        Axis yAxis = barModel.getAxis(AxisType.Y);
		        yAxis.setLabel("Availibility en % ");
		        yAxis.setMin(0);
		        yAxis.setMax(100);
		    }
}

