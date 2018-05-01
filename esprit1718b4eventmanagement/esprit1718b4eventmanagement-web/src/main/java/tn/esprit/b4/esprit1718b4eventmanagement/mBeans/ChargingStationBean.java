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

import tn.esprit.b4.esprit1718b4eventmanagement.EquiBean.EquipementBean;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;
@FacesComponent("ChargingStationBean")
@ManagedBean(name="ChargingStationBean")
@SessionScoped
public class ChargingStationBean implements Serializable {
	private int code;
	private String naturepost;
	private int nbday;
	private int nbhours;
	private String description;
	private static final long serialVersionUID = 1L;
	private ChargingStationPK chargingstationPK;
	private User user;
	private Equipment equipement;
	private List <User> UserList;
	@EJB
	ChargingStationService ChargingStationServices;
	@EJB
	EquipementService EquipementService;
	@EJB
	UserService UserServices;
	private List <ChargingStation> ChargingStations;
	private List <Equipment> EquipementList;
    @PostConstruct
    public void init() throws NamingException {
    	
    }
    
	public List<Equipment> getEquipementList() {
		EquipementList= EquipementService.DisplayEquipment();
		return EquipementList;
	}
	
	
	public void setEquipementList(List<Equipment> equipementList) {
		EquipementList = equipementList;
	}
	public List<User> getUserList() {
		UserList =UserServices.DisplayUser();
		return UserList;
	}
	public void setUserList(List<User> userList) {
		UserList = userList;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNaturepost() {
		return naturepost;
	}
	public void setNaturepost(String naturepost) {
		this.naturepost = naturepost;
	}
	public int getNbday() {
		return nbday;
	}
	public void setNbday(int nbday) {
		this.nbday = nbday;
	}
	public int getNbhours() {
		return nbhours;
	}
	public void setNbhours(int nbhours) {
		this.nbhours = nbhours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ChargingStationPK getChargingstationPK() {
		return chargingstationPK;
	}
	public void setChargingstationPK(ChargingStationPK chargingstationPK) {
		this.chargingstationPK = chargingstationPK;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Equipment getEquipement() {
		return equipement;
	}
	public void setEquipement(Equipment equipement) {
		this.equipement = equipement;
	}
	
	public List<ChargingStation> getChargingStations() {
		ChargingStations=ChargingStationServices.DisplayChargingStation();
		return ChargingStations;
	}
	public void setChargingStations(List<ChargingStation> chargingStations) {
		ChargingStations = chargingStations;
	}

	public void delete(Integer idEquipement,Integer idUser)
	{
		
		ChargingStationServices.deleteChargingStation(idEquipement, idUser);
	}
	
	public void AddChargingStation()
	{ 
	  ChargingStation ChS = new ChargingStation();
	  ChS.setCode(code);
	  ChS.setDescription(description);
	  ChS.setNaturepost(naturepost);
	  ChS.setNbday(nbday);
	  ChS.setNbhours(nbhours);
//	  ChS.setEquipement(equipement);
//	  ChS.setUser(user);
//	  e=equipement.getId();
//	  u=user.getId();
		//ChargingStationServices.ajouter(new ChargingStation(code, naturepost, nbday, nbhours, description, user, equipement));
	ChargingStationServices.addChargingStation(4, 4, ChS);
	}

	
	
}
