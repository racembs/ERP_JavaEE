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
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeService;

@FacesComponent("OperatingRangeBean")
@ManagedBean(name="OperatingRangeBean")
@SessionScoped
public class OperatingRangeBean implements Serializable {

	private int idoptrange;
	private String code;
	private String designation;
	private String stakingcondition;
	private int deadline;
	public static int n;
	private static final long serialVersionUID = 1L;
	private List <OperatingRange> OperatingRanges;
	@EJB
	public OperatingRangeService OperatingRangeServices;
	@EJB
	public ArticleService ArticleServices;
	private TreeNode root;
	
    @PostConstruct
    public void init() throws NamingException {
    	Tree();
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
    	n=opt.getIdoptrange();
 	
	}
    public void Confirm(){
    	OperatingRange opt =OperatingRangeServices.find(n);
    	opt.setCode(code);
    	opt.setDeadline(deadline);
    	opt.setDesignation(designation);
    	opt.setStakingcondition(stakingcondition);
    	OperatingRangeServices.updateOperatingRange(opt);
    	   		
    	
    }
    
    public void Tree ()
    {
  	
    	root= new DefaultTreeNode("Root",null);
        List<OperatingRange> list = OperatingRangeServices.DisplayOperatingRange();
    
        List<Article> listA = ArticleServices.DisplayArticle();

      
        
   
       // ObservableList<OperatingRange> items22 = FXCollections.observableArrayList(list);
       // System.out.println(items22.get(0).getDesignation());
      //  idTab.setItems(items22);
        
       // TreeItem<String> GPAO = new TreeItem<String>("GPAO");
        
        for (int a = 0; a < listA.size(); a++) {
        
  	

  	       // TreeItem<String> Article = new TreeItem<String>(itemsA.get(a).getArticleCode());
  	        //Article.setValue(itemsA.get(a).getArticleCode());
        	TreeNode node0=new DefaultTreeNode(listA.get(a).getArticleCode());
  	        root.getChildren().add(node0);
  	      //  ObservableList<OperatingRange> items = FXCollections.observableArrayList(listA.get(a).getOperatingranges());
  	        for (OperatingRange operatingRange : listA.get(a).getOperatingranges()) {
  	        //	TreeItem<String> Gammes = new TreeItem<String>(operatingRange.getCode());
  	    		//Gammes.setValue(operatingRange.getCode());
  	    		TreeNode node1=new DefaultTreeNode(operatingRange.getCode());
  	    		node0.getChildren().add(node1);
  	    		
  	    	
  			}

         
  	        
       //  GPAO.getChildren().addAll(Article);
       //  idTree.setRoot(GPAO);
	    	 
        }

        
  	 
    }
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}

    
    

    
    
}
