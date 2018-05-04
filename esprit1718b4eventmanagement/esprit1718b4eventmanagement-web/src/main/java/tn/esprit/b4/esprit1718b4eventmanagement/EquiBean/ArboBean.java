package tn.esprit.b4.esprit1718b4eventmanagement.EquiBean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceLocal;



@FacesComponent("arboBean")
@ManagedBean(name="arboBean")
@SessionScoped
public class ArboBean  {
	/**
	 * 
	 */
	private static TreeNode selectedNode;
	private static final long serialVersionUID = 1L;
	private TreeNode root;
	private static Arboresence rb=new Arboresence();
	private static Arboresence rb1=new Arboresence();
	private static Equipment equi=new Equipment();
	private static String type;
	private static String name;
	@EJB
	private ArboresenceServiceLocal ServiceLocal;
	  @PostConstruct
	    public void init() throws NamingException {fill3();
	    }
	

	  public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void fill3() {
		  List<Arboresence> Arbo=new ArrayList<>();
		  List<Arboresence> Arbo1=new ArrayList<>();
		  List<Arboresence> Arbo2=new ArrayList<>();
		  List<Arboresence> Arbo3=new ArrayList<>();
		  List<Arboresence> Arbo4=new ArrayList<>();
		  
		  List<ArboPereFis> Arbolist2=new ArrayList<>();
		  List<ArboPereFis> Arbolist3=new ArrayList<>();
		  List<ArboPereFis> Arbolist4=new ArrayList<>();
		  List<ArboPereFis> Arbolist5=new ArrayList<>();
			root = new DefaultTreeNode("Root", null);	
			 
			 List<Arboresence> arbo =  ServiceLocal.getPereArbo("Principale");
			 
	for(int i=0;i<arbo.size();i++) {
				
			
				 TreeNode newItemarticlePere = new DefaultTreeNode(arbo.get(i));
				 root.getChildren().add(newItemarticlePere);
				 List<ArboPereFis>  Arbolist1= ServiceLocal.getFilsArbo(arbo.get(i).getId());
				//init 1	
				 for(int ii1=0;ii1<Arbolist1.size();ii1++) {
					 Arbolist2= ServiceLocal.getFilsArbo(Arbolist1.get(ii1).getArboFils().getId());
					 Arbo.add(ServiceLocal.findArboresence(Arbolist1.get(ii1).getArboFils().getId()));
						
					
					 TreeNode newItemarticlePere1 = new DefaultTreeNode(Arbo.get(ii1));
					 newItemarticlePere.getChildren().add(newItemarticlePere1);
				//	init 2 
					 for(int ii2=0;ii2<Arbolist2.size();ii2++) {
						 Arbolist3= ServiceLocal.getFilsArbo(Arbolist2.get(ii2).getArboFils().getId());
						 Arbo2.add(ServiceLocal.findArboresence(Arbolist2.get(ii2).getArboFils().getId()));
							
							
						 TreeNode newItemarticlePere2= new DefaultTreeNode(Arbo2.get(ii2));
						 newItemarticlePere1.getChildren().add(newItemarticlePere2);
					 
//							//init 3
						 for(int ii3=0;ii3<Arbolist3.size();ii3++) {
							 Arbolist4= ServiceLocal.getFilsArbo(Arbolist3.get(ii3).getArboFils().getId());
							 Arbo3.add(ServiceLocal.findArboresence(Arbolist3.get(ii3).getArboFils().getId()));
							 
					
				
								
							 TreeNode newItemarticlePere3= new DefaultTreeNode(Arbo3.get(ii3));
							 newItemarticlePere2.getChildren().add(newItemarticlePere3);
						 
							//init 4
						 for(int ii4=0;ii4<Arbolist4.size();ii4++) {
							 Arbolist5= ServiceLocal.getFilsArbo(Arbolist4.get(ii4).getArboFils().getId());
							 Arbo4.add(ServiceLocal.findArboresence(Arbolist4.get(ii4).getArboFils().getId()));
							 
					
								
							 TreeNode newItemarticlePere4= new DefaultTreeNode(Arbo4.get(ii4));
							 newItemarticlePere3.getChildren().add(newItemarticlePere4);
						 }
						 }
					 }
					 
				 }
				 }
		
		
	 
	 		 }



	public ArboresenceServiceLocal getServiceLocal() {
		return ServiceLocal;
	}public ArboBean() {
		// TODO Auto-generated constructor stub
	}
	public void Refreche() {
		fill3();
		
	}
public String doSaveOrUpdatearbo() {
		if(type.equals("Principale"))
		{
			
	ServiceLocal.addArbo(new Arboresence(name,"Principale"));}
		else if(type.equals("Secondaire"))
		{
			ServiceLocal.addArbo(new Arboresence(name,"Secondaire"));
		ServiceLocal.addArbo(equi.getArboresence().getId(), ServiceLocal.getArbo(name).getId());	

		}
		fill3();
		
		return "Arbo.xhtml?faces-redirect=true";
	}


	public Equipment getEqui() {
	return equi;
}



public void setEqui(Equipment equi) {
	this.equi = equi;
}



	public Arboresence getRb1() {
		return rb1;
	}



	public void setRb1(Arboresence rb1) {
		this.rb1 = rb1;
	}



	public void setServiceLocal(ArboresenceServiceLocal serviceLocal) {
		ServiceLocal = serviceLocal;
	}



	public void setServiceLocal(ArboresenceService serviceLocal) {
		ServiceLocal = serviceLocal;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getArboperfis(Arboresence n) {
		return n.getName();
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Arboresence getRb() {
		return rb;
	}



	public void setRb(Arboresence rb) {
		this.rb = rb;
	}

	 public void displaySelectedSingle() {
	        if(selectedNode != null) {
	            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }
	    }
	     
	
}
