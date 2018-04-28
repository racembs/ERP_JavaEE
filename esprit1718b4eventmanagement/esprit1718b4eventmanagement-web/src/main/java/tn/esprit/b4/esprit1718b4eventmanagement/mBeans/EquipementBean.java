package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceLocal;


@ManagedBean
@ViewScoped
public class EquipementBean {
	@EJB
	private EquipementServiceLocal equipementServiceLocal;
	private List<Equipment> equipments = new ArrayList<>();
private   static Equipment equipement1;


	@PostConstruct
	public void init() {
		equipments = equipementServiceLocal.getAllEquipment();
	
	}

	private Equipment equipment = new Equipment();
	
	private static Equipment selectedEquipment;



	
	public String UpdateB(Equipment equi){
		
				 equipement1=equi;
				 selectedEquipment=equi;
		return "UpdateEquipement.xhtml?faces-redirect=true";
		    
		}

	public  void doSaveOrUpdateEquipment() {
		
		equipementServiceLocal.addEquippement(equipment);
	
	}		

		
	public  String  Update()
	{
		equipment=equipementServiceLocal.findEquipementBySerie(selectedEquipment.getSerialNum());
	equipment.setDescription(equipement1.getDescription());
	equipment.setFabriquant(equipement1.getFabriquant());
	equipment.setMarque(equipement1.getMarque());
	equipment.setState(equipement1.getState());
	equipment.setSerialNum(equipement1.getSerialNum());

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

	

}
