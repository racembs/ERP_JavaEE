package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;

@FacesComponent("OperatingRangeBean")
@ManagedBean(name="OperatingRangeBean")
@SessionScoped
public class OperatingRangeBean implements Serializable {

	private int idoptrange;
	private String code;
	private String designation;
	private String stakingcondition;
	private int deadline;
	public static Integer n;
	private static final long serialVersionUID = 1L;
	private List <OperatingRange> OperatingRanges;
	@EJB
	public OperatingRangeService OperatingRangeServices;

	
    @PostConstruct
    public void init() throws NamingException {
    	
    }
	public int getIdoptrange() {
		return idoptrange;
	}
	public void setIdoptrange(int idoptrange) {
		this.idoptrange = idoptrange;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getStakingcondition() {
		return stakingcondition;
	}
	public void setStakingcondition(String stakingcondition) {
		this.stakingcondition = stakingcondition;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public List<OperatingRange> getOperatingRanges() {
		OperatingRanges= OperatingRangeServices.DisplayOperatingRange();
		return OperatingRanges;
	}
	public void setOperatingRanges(List<OperatingRange> operatingRanges) {
		OperatingRanges = operatingRanges;
	}
    
    public void delete(Integer idOptR) {
    	OperatingRangeServices.deleteOperatingRange(idOptR);
	}
    
    public void Add() {
    
    	OperatingRangeServices.addOperatingRange(new OperatingRange(code, designation, stakingcondition, deadline));
	}
    
    public void update(OperatingRange opt) {
    	this.setCode(opt.getCode());
    	this.setDesignation(opt.getDesignation());
    	this.setStakingcondition(opt.getStakingcondition());
    	this.setDeadline(opt.getDeadline());
    	n=getIdoptrange();
 	
	}
    public void Confirm(){
    	OperatingRangeServices.updateOperatingRange(new OperatingRange(n,code, designation, stakingcondition, deadline));
    	   		
    	
    }
    
    
    
    
}
