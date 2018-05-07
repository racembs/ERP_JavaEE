package tn.esprit.b4.esprit1718b4eventmanagement.EquiBean;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceLocal;


@ManagedBean
@ViewScoped
public class EquipementBean {
	@EJB
	private EquipementServiceLocal equipementServiceLocal;
	private List<Equipment> equipments = new ArrayList<>();
	private  List<Equipment> equipments1;
	private static Equipment equipement1;
	private int Id;
	private Arboresence arbo;
	private String SerialNum;
	private static UploadedFile file;
	private String Description;

	private String State;

	private String EISDate;

	private String Fabriquant;

	private String Marque;

	private String Lieu;
	
	private static String image;
	private int selectedOrderId;
	
	public int getSelectedOrderId() {
		return selectedOrderId;
	}

	public void setSelectedOrderId(int selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}

	@PostConstruct
	public void init() {
		equipments = equipementServiceLocal.getAllEquipment();
	
	}

	private Equipment equipment = new Equipment();
	
	private static Equipment selectedEquipment;


	  
	  public void handleFileUpload(FileUploadEvent event) {
			UploadedFile file = event.getFile();
			image=file.getFileName();
	  }
	
	public String UpdateB(Equipment equi){
	
				 equipement1=equi;
				 selectedEquipment=equi;
		return "UpdateEquipement.xhtml?faces-redirect=true";
		    
		}

	public  void doSaveOrUpdateEquipment() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	      Calendar date = Calendar.getInstance();
	   String  d = df.format(date.getTime());
	  
		equipementServiceLocal.addEquippement(new Equipment(SerialNum, Description, State,d, Fabriquant, Marque,"","", arbo));
		FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Successful Add" ) );
	
	}
public  String find() {
	if(equipment.getFabriquant().equals("fabriquant"))
	{
		equipments1=equipementServiceLocal.findEquipementFab(equipment.getImage());
	}
	else if (equipment.getFabriquant().equals("serialNum"))
	{
		equipments1=equipementServiceLocal.findEquipementSerialNum(equipment.getImage());
	}
	else if (equipment.getFabriquant().equals("Marque"))
	{
		equipments1=equipementServiceLocal.findEquipementMarque(equipment.getImage());
	}
	else if (equipment.getImage().equals(""))
	{
		return "ListEquipement.xhtml?faces-redirect=true";
	}
	return "RechercheEquipement.xhtml?faces-redirect=true";
	}
public String Refrech(){return "ListEquipement.xhtml?faces-redirect=true";}
	public  String Delete(Equipment equi) {
		
		equipementServiceLocal.DeleteEqupment(equi.getId());;
		return "ListEquipement.xhtml?faces-redirect=true";
	}
		
	public  String  Update()
	{
		equipment=equipementServiceLocal.findEquipementBySerie(selectedEquipment.getSerialNum());
	equipment.setDescription(equipement1.getDescription());
	equipment.setFabriquant(equipement1.getFabriquant());
	equipment.setMarque(equipement1.getMarque());
	equipment.setState(equipement1.getState());
	equipment.setSerialNum(equipement1.getSerialNum());
	equipment.setArboresence(equipement1.getArboresence());

		equipementServiceLocal.updateEquipment(equipment);
		return "ListEquipement.xhtml?faces-redirect=true";
	}
	
	
	
	
	public EquipementBean(EquipementServiceLocal equipementServiceLocal, List<Equipment> equipments,
			Equipment equipment) {
		super();
		this.equipementServiceLocal = equipementServiceLocal;
		this.equipments = equipments;
		this.equipment = equipment;
	}

	
	public List<Equipment> getEquipments() {
		return equipments;
	}
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	public Equipment getSelectedEquipment() {
		return selectedEquipment;
	}

	public void setSelectedEquipment(Equipment selectedEquipment) {
		this.selectedEquipment = selectedEquipment;
	}


	public EquipementBean(List<Equipment> equipments, Equipment equipment) {
		super();
		this.equipments = equipments;
		this.equipment = equipment;
	}


	public EquipementBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Equipment getEquipement1() {
		return equipement1;
	}

	public void setEquipement1(Equipment equipement1) {
		this.equipement1 = equipement1;
	}

	public List<Equipment> getEquipments1() {
		return equipments1;
	}

	public void setEquipments1(List<Equipment> equipments1) {
		this.equipments1 = equipments1;
	}

	public EquipementBean(List<Equipment> equipments1) {
		super();
		this.equipments1 = equipments1;
	}

	
	  public void info() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
	    }
	 
	    public void warn() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
	    }
	 
	    public void error() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
	    }
	 
	    public void fatal() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
	    }

		public EquipementServiceLocal getEquipementServiceLocal() {
			return equipementServiceLocal;
		}

		public void setEquipementServiceLocal(EquipementServiceLocal equipementServiceLocal) {
			this.equipementServiceLocal = equipementServiceLocal;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getSerialNum() {
			return SerialNum;
		}

		public void setSerialNum(String serialNum) {
			SerialNum = serialNum;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}

		public String getState() {
			return State;
		}

		public void setState(String state) {
			State = state;
		}

		public String getEISDate() {
			return EISDate;
		}

		public void setEISDate(String eISDate) {
			EISDate = eISDate;
		}

		public String getFabriquant() {
			return Fabriquant;
		}

		public void setFabriquant(String fabriquant) {
			Fabriquant = fabriquant;
		}

		public String getMarque() {
			return Marque;
		}

		public void setMarque(String marque) {
			Marque = marque;
		}

		public String getLieu() {
			return Lieu;
		}

		public void setLieu(String lieu) {
			Lieu = lieu;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public void setEquipments(List<Equipment> equipments) {
			this.equipments = equipments;
		}

		public Arboresence getArbo() {
			return arbo;
		}

		public void setArbo(Arboresence arbo) {
			this.arbo = arbo;
		}

		public UploadedFile getFile() {
			return file;
		}

		public void setFile(UploadedFile file) {
			this.file = file;
		}
		
	    
}
