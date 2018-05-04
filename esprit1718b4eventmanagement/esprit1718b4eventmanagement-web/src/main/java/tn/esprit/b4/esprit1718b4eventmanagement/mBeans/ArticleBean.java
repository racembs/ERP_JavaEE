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
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
@FacesComponent("treeBean")
@ManagedBean(name="treeBean")
@SessionScoped
public class ArticleBean implements Serializable {
	private static int idArticle;
	private String articleCode;
	private String description;
	private String unitCode;
	private String type;
	private float pmp;
	private int quantity;
	private List<Article> articles;
	 @EJB
	ArticleService articleService;
	private static final long serialVersionUID = 3350653785168926842L;
	private TreeNode root;
	private static TreeNode selectedNode;  
    
	
	
    @PostConstruct
    public void init() throws NamingException {
    	fillTreeNode();

		 }
    
    
    public void updateNomenclature() {
    	articleService.addNomenclature(articleService.findArticleByCode(selectedNode.toString()).get(0).getId()
    			, articleService.findArticleByCode(articleCode).get(0).getId(), quantity);
    	fillTreeNode();
    }
    public void deleteNode() { 
    	Nomenclature nomenclature=articleService.findNomenclature(articleService.findArticleByCode(selectedNode.getParent().toString()).get(0).getId(),
    			articleService.findArticleByCode(selectedNode.toString()).get(0).getId()).get(0);
    	articleService.DeleteNomenclature(nomenclature);
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
         
        selectedNode = null;
    }  
    
public void addArticle() {
	articleService.addArticle(new Article(articleCode, description, unitCode, type, pmp, quantity));
}



public void deleteArticle(Integer idArticle) {
	articleService.DeleteArticle(idArticle);
	//this.quantity=idArticle;
}


public void reset() {
	this.articleCode="";
	this.description="";
	this.quantity=0;
	this.pmp=0;
	this.unitCode="";
}
public void setArticle(Article article){
	this.articleCode=article.getArticleCode();
	this.description=article.getDescription();
	this.quantity=article.getQuantity();
	this.pmp=article.getPmp();
	this.unitCode=article.getUnitCode();
	this.type=article.getType();
	this.idArticle=article.getId();
	
}
public void updteArticle() {
	Article article=new Article(articleCode, description, unitCode, type, pmp, quantity);
	article.setId(this.getIdArticle());
	articleService.updateArticle(article);
}
    

 public void fillTreeNode() {
	 
	 root = new DefaultTreeNode("Matiére Premiére", null);
		 List<Nomenclature> listNomenclature;
		 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
		 listNomenclature=articleService.getAllFinalArticleNomenclature();

		
		 
	for(int i=0;i<listNomenclature.size();i++) {
		if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
			produitFini.add(listNomenclature.get(i));
			
		}
	}

	 

	 for(int i=0;i<produitFini.size();i++) {
		 List<Nomenclature> articlePere=produitFini;
		 
		 TreeNode newNode = new DefaultTreeNode(articlePere.get(i).getArticleFils().getArticleCode());
	
		 root.getChildren().add(newNode);
		 
		
		
		 
		 ArrayDeque <TreeNode> queue=new ArrayDeque<>();
		 queue.add(newNode);
		 
		
		 while(!queue.isEmpty()) {
			 
		
			TreeNode TreeItemHead=queue.getFirst();
			 queue.removeFirst();
			
			 List<Nomenclature> listNomenclatureFils=articleService.getFilsArticles(articleService.findArticleByCode(TreeItemHead.toString()).get(0).getId());
			 for(int j=0;j<listNomenclatureFils.size();j++) {
				
				 TreeNode newNodeFils = new DefaultTreeNode(listNomenclatureFils.get(j).getArticleFils().getArticleCode());
				 TreeItemHead.getChildren().add(newNodeFils);
				 queue.addLast(newNodeFils);
				 
		
		
			 }
		 }
		 
		 
		
	 }
 }
 
 
 
 
 public ArticleService getArticleService() {
	return articleService;
}
 
 
 
 
 
 
 
 



public List<Article> getArticles() {
	List<Article>list =articleService.getAllArticles();
	for(int i=0;i<list.size()-1;i++) {
		for(int j=0;j<list.size();j++) {
			if(i!=j&&list.get(i).getArticleCode().equals(list.get(j).getArticleCode())) {
				list.remove(list.get(j));
			}
		}
	}
	return list;
}


public void setArticles(List<Article> articles) {
	this.articles = articles;
}

public static int getIdArticle() {
	return idArticle;
}
public static void setIdArticle(int idArticle) {
	ArticleBean.idArticle = idArticle;
}
public String getArticleCode() {
	return articleCode;
}
public void setArticleCode(String articleCode) {
	this.articleCode = articleCode;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUnitCode() {
	return unitCode;
}
public void setUnitCode(String unitCode) {
	this.unitCode = unitCode;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public float getPmp() {
	return pmp;
}
public void setPmp(float pmp) {
	this.pmp = pmp;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public void setArticleService(ArticleService articleService) {
	this.articleService = articleService;
}

public int getQuantity(Nomenclature n) {
	return n.getQuantity();
}

public String getArticleFilsCode(Nomenclature n) {
	return n.getArticleFils().getArticleCode();
}
public Article getArticleFils(Nomenclature n) {
	return n.getArticleFils();
}
	public TreeNode getRoot() {
        return root;
    }
	public TreeNode getSelectedNode() {
		return selectedNode;
	}
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	
}