package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementService;
@FacesComponent("DashboardBean")
@ManagedBean(name="DashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {
	private Equipment equipement;
	public String serialNum;
	private List <Equipment> EquipementList;
	@EJB
	EquipementService EquipementService;
	 @PostConstruct
	    public void init() throws NamingException {
	    	
	    }
	 
	    public List<String> findEquipement(String query){
	        List<Equipment> list=EquipementService.getAllEquipment();
	        List<String> sList =new ArrayList<>();
	        for(int i=0;i<list.size();i++){
	        	if(list.get(i).getSerialNum().toUpperCase().contains(query)||list.get(i).getSerialNum().toLowerCase().contains(query)){
	        		sList.add(list.get(i).getSerialNum());
	        	}
	        
	        }
	        return sList;
	        }
	    
	    
		public List<Equipment> getEquipementList() {
			EquipementList= EquipementService.DisplayEquipment();
			return EquipementList;
		}
		
		
		public void setEquipementList(List<Equipment> equipementList) {
			EquipementList = equipementList;
		}
		
		public Equipment getEquipement() {
			return equipement;
		}
		public void setEquipement(Equipment equipement) {
			this.equipement = equipement;
		}
		public String getSerialNum() {
			return serialNum;
		}
		public void setSerialNum(String serialNum) {
			this.serialNum = serialNum;
		}
	 
	 
}
