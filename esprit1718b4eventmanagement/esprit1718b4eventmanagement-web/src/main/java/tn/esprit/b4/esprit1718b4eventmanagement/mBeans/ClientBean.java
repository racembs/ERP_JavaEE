package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

@ManagedBean
@ViewScoped
public class ClientBean implements Serializable {
	private int idClient;
	private String code;
	private String company;
	private String email;
	private long phoneNumber;
	private List<Client> clients;
	
	@EJB
	ClientServiceLocal clientService;
	 
	private static final long serialVersionUID = 3350653785168926842L;
    
    @PostConstruct
    public void init() throws NamingException {
    		
		}
    
    public void addClient(){
    	Client client = new Client(company, email, phoneNumber);
    	clientService.addClient(client);
    	String codess="";
		for (int i = 0; i < 3; i++) {
			codess=codess+client.getCompany().charAt(i);
		}
		client.setCode(codess+(client.getId()*12+7));
		clientService.update(client);
		reset();
    }
    
    public void clientForUpdate(Client client){
    	this.setIdClient(client.getId());
    	this.setCode(client.getCode());
    	this.setCompany(client.getCompany());
    	this.setEmail(client.getEmail());
    	this.setPhoneNumber(client.getPhoneNumber());
    }
    
    public void delete(Client client){
    	clientService.delete(client);
    }
    
    public void updateClient(){
    	Client client = new Client(company, email, phoneNumber);
    	client.setCode(code);
    	client.setId(idClient);
    	clientService.update(client);
    }

	public void reset() {
		this.company="";
		this.email="";
		this.phoneNumber=0;
	}

	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Client> getClients() {
		return clients = clientService.findAll();
	}
	
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
 
 

}